package com.testchatbot.domain.chatgpt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "chatgpt")
public record ChatGPTInfo(
        String model,
        String apiKey,
        Integer maxToken,
        String promptUrl,
        String modelUrl
) {
}
