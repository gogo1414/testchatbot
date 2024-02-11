package com.testchatbot.web.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {


    private Key secretKey;

    @Value("${security.jwt.token.secret-key}")
    private String SECRET_KEY;

    @Value("${security.jwt.token.token-period}")
    private Long TOKEN_PERIOD;

    @Value("${security.jwt.token.refresh-period}")
    private Long REFRESH_PERIOD;

    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET_KEY));
    }

    public String createToken(String uid) {

        Claims claims = Jwts.claims()
                .setIssuer("Admin")
                .setAudience(uid);
//        claims.put("role", role);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject("Access Token")
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_PERIOD))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
