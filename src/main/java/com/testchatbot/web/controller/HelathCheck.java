package com.testchatbot.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelathCheck {

    private final Logger LOGGER = LoggerFactory.getLogger(HelathCheck.class);

    @GetMapping("/check")
    public String healthCheck( ) {
        LOGGER.info("[HealthCheck] performs of {}", "Server processing");
        return "Welcome to ChatBot Service";
    }

    @PostMapping("/log-test")
    public void logTest() {
        LOGGER.trace("Trace Log");
        LOGGER.debug("debug Log");
        LOGGER.info("Info Log");
        LOGGER.warn("Warn Log");
        LOGGER.error("Error Log");
    }

}
