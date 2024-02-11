package com.testchatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class TestchatbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestchatbotApplication.class, args);
    }

}
