package com.haeahn.common.global.threadlocal;

import com.haeahn.common.global.dto.CustomHeaderDto;

public class CustomHeaderContext {
    private static final ThreadLocal<CustomHeaderDto> CLIENT_INFO_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 클라이언트 정보 설정
     */
    public static void set(CustomHeaderDto clientInfo) {
        CLIENT_INFO_THREAD_LOCAL.set(clientInfo);
    }

    /**
     * 클라이언트 정보 조회
     */
    public static CustomHeaderDto get() {
        return CLIENT_INFO_THREAD_LOCAL.get();
    }

    /**
     * 클라이언트 정보가 설정되어 있는지 확인
     */
    public static boolean hasContext() {
        return CLIENT_INFO_THREAD_LOCAL.get() != null;
    }

    /**
     * ThreadLocal 정리 (메모리 누수 방지)
     */
    public static void clear() {
        CLIENT_INFO_THREAD_LOCAL.remove();
    }
}
