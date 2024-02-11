package com.testchatbot.domain.auth;

import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
public class NaverParams implements OauthParams{

    private String authorizationCode;
    private String state;

    @Override
    public OauthProvider oauthProvider() {
        return OauthProvider.NAVER;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("code", authorizationCode);
        body.add("state", state);

        return body;
    }

    @Override
    public String getAuthorizationCode() {

        return authorizationCode;
    }
}
