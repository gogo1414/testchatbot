package com.testchatbot.domain.message;

import com.testchatbot.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Message Dto
 *
 * @author : kim
 * @since : 02/05/24
 */
@Getter
@NoArgsConstructor
@Entity
public class Message extends BaseTimeEntity {

    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String message;

    @Builder
    public Message(String message,
                   String name) {
        this.message = message;
        this.name = name;
    }
}
