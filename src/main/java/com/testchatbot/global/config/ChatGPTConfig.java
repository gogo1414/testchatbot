package com.testchatbot.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * ChatGPT에서 사용하는 환경 구성
 *
 * @author : kim
 * @since : 01/31/24
 */

@Configuration
public class ChatGPTConfig {

    @Value("${chatgpt.api-key}")
    private String secretKey;

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate;
    }

    @Bean
    public HttpHeaders httpHeaders() {

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}
