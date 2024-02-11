package com.testchatbot.web.dto.chatgpt.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ModelListResponse {

    private String object;
    private List<Map<String, Object>> data;
}
