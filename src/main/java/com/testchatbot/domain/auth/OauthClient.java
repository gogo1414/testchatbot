package com.testchatbot.domain.auth;

import com.testchatbot.domain.member.OauthMember;

public interface OauthClient {

    public OauthProvider oauthProvider();
    public String getOauthLoginToken(OauthParams oauthParams);
    public OauthMember getMemberInfo(String accessToken);
}
