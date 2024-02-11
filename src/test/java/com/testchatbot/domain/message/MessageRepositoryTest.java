package com.testchatbot.domain.message;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * MessageTest
 *
 * @author : kim
 * @since :02/05/24
 */
@SpringBootTest
public class MessageRepositoryTest {

    @Autowired
    MessageRepository messageRepository;

    @AfterEach
    public void cleanUp() {
        messageRepository.deleteAll();
    }

    @Test
    public void messageLoad() {

        //given
        String name = "kim";
        String message = "안녕하세요 어쩌라구욧";
        LocalDateTime now = LocalDateTime.of(2024,02,05,0,0,0,0);

        messageRepository.save(Message.builder()
                .name(name)
                .message(message)
                .build());

        //when
        List<Message> messageList = messageRepository.findAll();

        Message message1 = messageList.get(0);

        //then
        assertThat(message1.getName()).isEqualTo(name);
        assertThat(message1.getMessage()).isEqualTo(message);

    }
}
