package com.testchatbot.web.dto.chatgpt.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Front의 ChatGpt 질문 Dto
 *
 * @author : kim
 * @since : 02/02/24
 *
 */
@Getter
@NoArgsConstructor
public class QuestionRequestDto {

    private String question;
}
