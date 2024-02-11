package com.testchatbot.web.dto.chatgpt.response;

import com.testchatbot.web.dto.chatgpt.ChatGptMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * ChatGpt 답변 DTO
 *
 * @author : kim
 * @since : 02/02/24
 */
@Getter
@NoArgsConstructor
public class ChatGptResponseDto {

    private String id;
    private String object;
    private long created;
    private String model;
    private Usage usage;
    private List<Choice> choices;

    @Getter
    @Setter
    public static class Usage {

        private int promptTokens;

        private int completionTokens;

        private int totalTokens;
    }

    @Getter
    @Setter
    public static class Choice {

        private ChatGptMessage message;

        private String finishReason;

        private int index;
    }
}
