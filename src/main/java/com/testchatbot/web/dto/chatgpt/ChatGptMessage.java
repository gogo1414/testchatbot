package com.testchatbot.web.dto.chatgpt;

import lombok.*;

/**
 * ChatGPT response messages list
 *
 * @author : kim
 * @since : 02/02/24
 */

@Data
@NoArgsConstructor
public class ChatGptMessage {

    private String role;
    private String content;

    @Builder
    public ChatGptMessage(String role,
                           String content) {
        this.role = role;
        this.content = content;
    }
}
