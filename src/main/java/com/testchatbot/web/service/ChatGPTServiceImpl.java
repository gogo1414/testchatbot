package com.testchatbot.web.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testchatbot.domain.chatgpt.ChatGPTInfo;
import com.testchatbot.global.config.ChatGPTConfig;
import com.testchatbot.web.dto.chatgpt.ChatGptMessage;
import com.testchatbot.web.dto.chatgpt.request.ChatGptRequestDto;
import com.testchatbot.web.dto.chatgpt.response.ModelListResponse;
import com.testchatbot.web.dto.chatgpt.request.QuestionRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChatGPT Service 구현체
 *
 * @author : kim
 * @since : 01/31/24
 */
@RequiredArgsConstructor
@Service
public class ChatGPTServiceImpl implements ChatGPTService {

    private final ChatGPTConfig chatGPTConfig;
    private final ChatGPTInfo chatGPTInfo;
    private final Logger LOGGER = LoggerFactory.getLogger(ChatGPTServiceImpl.class);

    /**
     * 사용 가능한 모델 리스트를 조회하는 비즈니스 로직
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> modelList() {

        LOGGER.info("[ChatGPTServiceImpl] performs of {}", "checking the modelList");
        List<Map<String, Object>> resultList = null;

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();
        LOGGER.info("[ChatGPTServiceImpl] performs of {}", "making the headers");

        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
        ResponseEntity<String> response = chatGPTConfig.restTemplate()
                .exchange(
                        chatGPTInfo.modelUrl(),
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        String.class);

        try {
            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
            ObjectMapper om = new ObjectMapper();

            resultList = om.readValue(
                    response.getBody(),
                    ModelListResponse.class).getData();

        } catch (JsonMappingException e) {
            LOGGER.info("[ChatGPTServiceImpl] JsonMappingException :: {}", e.getMessage());
        } catch (JsonProcessingException e) {
            LOGGER.info("[ChatGPTServiceImpl] RuntimeException :: {}", e.getMessage());
        }

        return resultList;
    }

    /**
     * 모델이 유효한지 확인하는 비즈니스 로직
     *
     * @param modelName
     * @return
     */
    @Override
    public Map<String, Object> isValidModel(String modelName) {

        LOGGER.info("[+] 모델이 유효한지 조회합니다. 모델 : {}", modelName);
        Map<String, Object> result = null;

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
        ResponseEntity<String> response = chatGPTConfig.restTemplate()
                .exchange(
                        chatGPTInfo.modelUrl() + modelName,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        String.class);


        try {
            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
            ObjectMapper om = new ObjectMapper();

            result = om.readValue(
                    response.getBody(),
                    new TypeReference<>() {});

        } catch (IOException e) {
            LOGGER.info("[ChatGPTServiceImpl] IOException :: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * ChatGPT Chatting
     *
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> prompt(QuestionRequestDto questionRequestDto) {

        LOGGER.info("[+] 프롬프트를 수행합니다.");
        Map<String, Object> result = new HashMap<>();

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        String requestBody = "";
        ObjectMapper om = new ObjectMapper();

        ChatGptMessage message = ChatGptMessage.builder()
                .role("user")
                .content(questionRequestDto.getQuestion())
                .build();

        // [STEP3] properties의 model을 가져와서 객체에 추가합니다.
        ChatGptRequestDto requestDto = ChatGptRequestDto.builder()
                .maxTokens(chatGPTInfo.maxToken())
                .stream(false)
                .temperature(0.8)
                .model(chatGPTInfo.model())
                .messages(List.of(message))
                .build();

        LOGGER.info("생성된 requestDto : {}", requestDto);

        try {
            // [STEP4] Object -> String 직렬화를 구성합니다.
            requestBody = om.writeValueAsString(requestDto);

            LOGGER.info("입력된 requestBody : {}", requestBody);

        } catch (JsonProcessingException e) {
            LOGGER.info("[ChatGPTServiceImpl] JsonProcessingException :: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
        HttpEntity requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = chatGPTConfig.restTemplate()
                .exchange(
                        chatGPTInfo.promptUrl(),
                        HttpMethod.POST,
                        requestEntity,
                        String.class);

        try {
            // [STEP6] String -> HashMap 역직렬화를 구성합니다.
            result = om.readValue(
                    response.getBody(),
                    new TypeReference<>() {});

        }catch (JsonMappingException e) {
            LOGGER.info("[ChatGPTServiceImpl] JsonMappingException :: {}", e.getMessage());
        } catch (JsonProcessingException e) {
            LOGGER.info("[ChatGPTServiceImpl] JsonProcessingException :: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }
}
