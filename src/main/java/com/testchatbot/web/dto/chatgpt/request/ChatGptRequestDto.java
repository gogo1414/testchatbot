package com.testchatbot.web.dto.chatgpt.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testchatbot.web.dto.chatgpt.ChatGptMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * gpt-3.5-turbo 대화 요청 dto
 *
 * @author : kim
 * @since : 02/02/24
 */
@Getter
@NoArgsConstructor
public class ChatGptRequestDto {

    private String model;

    @JsonProperty("max_tokens")
    private Integer maxTokens;

    private Double temperature;

    private Boolean stream;

    private List<ChatGptMessage> messages;

    @Builder
    public ChatGptRequestDto(String model,
                             Integer maxTokens,
                             Double temperature,
                             Boolean stream,
                             List<ChatGptMessage> messages) {
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.stream = stream;
        this.messages = messages;
    }

}
