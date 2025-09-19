package com.haeahn.common.global.interceptor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * client header info
 *     - Authorization
 *     - Content-Type
 *     - X-Requested-With
 *     - X-Request-ID
 *     - X-Forwarded-For
 *     - X-Real-IP
 *     - X-Device-Type
 *     - X-Session-Id
 *     - X-Device-Id
 *     - X-App-Version
 *     - X-Client-User-Agent
 *     - User-Agent
 *     - X-Request-Uri
 *     - X-Device-Os
 *     - X-Service-Name
 */
@Data // getter, setter, toString, hashcode 자동 생성
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomHeaderDto {
    private String requestId;
    private Long timestamp;

    private String clientIp;    // X-Forwarded-For | X-Real-IP
    private String deviceType;  // X-Device-Type
    private String sessionId;   // X-Session-Id
    private String deviceId;    // X-Device-Id
    private String deviceOs;    // X-Device-Os
    private String appVersion;  // X-App-Version
    private String userAgent;   // User-Agent
    private String browserType; // User-Agent | X-Client-User-Agent
    private String requestUri;  // X-Request-Uri
    private String serviceName; // X-Service-Name

    private String accessToken;
    private String refreshToken;

    private String userId;
    private String userRole;

    // 기본값 설정을 위한 빌더 패턴
    public static CustomHeaderDto createDefault() {
        CustomHeaderDto header = new CustomHeaderDto();
//        header.setRequestId(UUID.randomUUID().toString().replace("-", ""));
        header.setSessionId(UUID.randomUUID().toString().replace("-", ""));
        header.setTimestamp(System.currentTimeMillis());

        header.setClientIp("");
        header.setDeviceType("");
        header.setDeviceId("");
        header.setDeviceOs("");
        header.setAppVersion("");
        header.setUserAgent("");
        header.setBrowserType("");
        header.setRequestUri("");
        header.setServiceName("");
        header.setAccessToken("");
        header.setRefreshToken("");
        return header;
    }

}
