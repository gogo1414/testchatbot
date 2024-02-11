package com.testchatbot.web.controller;

import com.testchatbot.web.dto.chatgpt.request.QuestionRequestDto;
import com.testchatbot.web.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ChatGPT API
 *
 * @author : kim
 * @since : 02/06/24
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatGpt")
public class ChatGPTController {

    private final Logger LOGGER = LoggerFactory.getLogger(ChatGPTController.class);
    private final ChatGPTService chatGPTService;

    /**
     * [API] ChatGPT 모델 리스트를 조회합니다.
     */
    @GetMapping("/modelList")
    public ResponseEntity<List<Map<String, Object>>> selectModelList() {

        long startTime = System.currentTimeMillis();
        LOGGER.info("[ChatGPTController] perform of {}", "getModelList");

        List<Map<String, Object>> resultList = chatGPTService.modelList();

        for(Map<String, Object> modelList : resultList) {
            LOGGER.info("[ChatGPTController] Response :: id = {}," +
                            " object = {}," +
                            " created = {}," +
                            " owned_by = {}," +
                            " Response Time = {}",
                    modelList.get("id"),
                    modelList.get("object"),
                    modelList.get("created"),
                    modelList.get("owned_by"),
                    (System.currentTimeMillis() - startTime)
            );
        }

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    /**
     * [API] ChatGPT 유효한 모델인지 조회합니다.
     *
     * @param modelName
     * @return
     */
    @GetMapping("/model")
    public ResponseEntity<Map<String, Object>> isValidModel(@RequestParam(name = "model") String modelName) {

        long startTime = System.currentTimeMillis();
        LOGGER.info("[ChatGPTController] perform of {}", "validateModel");

        Map<String, Object> model = chatGPTService.isValidModel(modelName);

        LOGGER.info("[ChatGPTController] Response :: id = {}," +
                        " object = {}," +
                        " created = {}," +
                        " owned_by = {}," +
                        " Response Time = {}",
                model.get("id"),
                model.get("object"),
                model.get("created"),
                model.get("owned_by"),
                (System.currentTimeMillis() - startTime)
        );

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /**
     * [API] ChatGPT 질문을 시작합니다.
     *
     * @return
     */
    @PostMapping("/prompt")
    public ResponseEntity<Map<String, Object>> selectPrompt(@RequestBody QuestionRequestDto questionRequestDto) {

        long startTime = System.currentTimeMillis();
        LOGGER.info("[ChatGPTController] perform of {}", "ChatGPT Chatting");

        Map<String, Object> result = chatGPTService.prompt(questionRequestDto);

        LOGGER.info("[ChatGPTController] Response :: id = {}, " +
                "object = {}, " +
                "created = {}, " +
                "model = {}, " +
                "choices = {}, " +
                "usage = {}, " +
                "system_fingerprint = {}",
                result.get("id"),
                result.get("object"),
                result.get("created"),
                result.get("model"),
                result.get("choices"),
                result.get("usage"),
                result.get("system_fingerprint"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
