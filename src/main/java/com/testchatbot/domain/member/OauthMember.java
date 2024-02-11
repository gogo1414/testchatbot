package com.testchatbot.domain.member;

import com.testchatbot.domain.auth.OauthProvider;

public interface OauthMember {

    public String getEmail();
    public String getName();
    OauthProvider getOauthProvider();
}
