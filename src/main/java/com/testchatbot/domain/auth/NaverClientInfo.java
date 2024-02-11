package com.testchatbot.domain.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.oauth.naver")
public record NaverClientInfo(
        String clientId,
        String clientSecret,
        String tokenUrl,
        String userUrl,
        String grantType
) {
}
