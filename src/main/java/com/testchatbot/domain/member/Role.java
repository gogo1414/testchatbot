package com.testchatbot.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Role 회원 권한
 *
 * @author : kim
 * @since : 02/03/24
 */
@Getter
@RequiredArgsConstructor
public enum Role {

    // GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
