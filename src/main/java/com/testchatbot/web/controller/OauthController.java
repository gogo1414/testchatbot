package com.testchatbot.web.controller;

import com.testchatbot.domain.auth.NaverParams;
import com.testchatbot.web.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OauthController {

    private final Logger LOGGER = LoggerFactory.getLogger(OauthController.class);
    private final OauthService oauthService;

    @PostMapping("/oauth/naver")
    public ResponseEntity<String> handleNaberLogin(@RequestBody NaverParams naverParams) {

        long startTime = System.currentTimeMillis();
        LOGGER.info("[OauthController] performs of {}", "Naver Login");

        String accessToken = oauthService.getMemberByOauthLogin(naverParams);
        LOGGER.info("[OauthController] Response :: accessToken = {}, " +
                "Response Time = {}",
                accessToken,
                (System.currentTimeMillis() - startTime)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("accessToken", "Baerer " + accessToken);

        return ResponseEntity.ok().headers(headers).body("Success");
    }
}
