package com.testchatbot.web.service;

import com.testchatbot.web.dto.chatgpt.request.QuestionRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ChatGPT 서비스 인터페이스
 *
 * @author : kim
 * @since : 01/31/24
 */

@Service
public interface ChatGPTService {

    List<Map<String, Object>> modelList();

    Map<String, Object> prompt(QuestionRequestDto questionRequestDto);

    Map<String, Object> isValidModel(String modelName);
}
