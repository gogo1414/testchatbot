package com.testchatbot.domain.auth;

import org.springframework.util.MultiValueMap;

public interface OauthParams {

    public OauthProvider oauthProvider();
    public String getAuthorizationCode();
    public MultiValueMap<String, String> makeBody();

}
