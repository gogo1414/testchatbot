package com.testchatbot.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * memberRepositoryTest
 * 
 * @author : kim
 * @since : 02/03/24
 * 
 * 통과
 */
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @Test
    public void memberLoad() {
        //given
        String email = "Test@test.com";
        String name = "kim";
        LocalDateTime now = LocalDateTime.of(2024,2,3,0,0);

        memberRepository.save(Member.builder()
                .email(email)
                .name(name)
                .build());

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Member member = memberList.get(0);
        assertThat(member.getEmail()).isEqualTo(email);
        assertThat(member.getName()).isEqualTo(name);
        assertThat(member.getCreatedDate()).isAfter(now);

    }

}
