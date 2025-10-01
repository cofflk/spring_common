package com.haeahn.common.global.threadlocal;

import com.haeahn.common.global.dto.AuthUserDto;

public class AuthContext {
    private static final ThreadLocal<AuthUserDto> AUTH_USER = new ThreadLocal<>();

    /**
     * 클라이언트 정보 설정
     */
    public static void set(AuthUserDto dto) {
        AUTH_USER.set(dto);
    }

    /**
     * 클라이언트 정보 조회
     */
    public static AuthUserDto get() {
        AuthUserDto dto = AUTH_USER.get();
        return dto;
    }

    /**
     * 클라이언트 정보가 설정되어 있는지 확인
     */
    public static boolean hasContext() {
        return AUTH_USER.get() != null;
    }

    /**
     * ThreadLocal 정리 (메모리 누수 방지)
     */
    public static void clear() {
        AUTH_USER.remove();
    }
}
