package com.testchatbot.web.service;

import com.testchatbot.domain.auth.OauthParams;
import com.testchatbot.domain.member.Member;
import com.testchatbot.domain.member.MemberRepository;
import com.testchatbot.domain.member.OauthMember;
import com.testchatbot.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class OauthService {

    private final MemberRepository memberRepository;
    private final RequestOauthInfoService requestOauthInfoService;
    private final JwtProvider jwtProvider;

    public String getMemberByOauthLogin(OauthParams oauthParams) {

        OauthMember oauthMember = requestOauthInfoService.request(oauthParams);

        Optional<Member> member = memberRepository.findByEmail(oauthMember.getEmail());

        String accessJwt = null;

        if(member == null) {
            memberRepository.save(Member.builder()
                    .email(oauthMember.getEmail())
                    .name(oauthMember.getName())
                    .role(Role.USER)
                    .build());
        }

        accessJwt = jwtProvider.createToken(oauthMember.getEmail());

        return accessJwt;
    }

}
