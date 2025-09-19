package com.haeahn.common.global.interceptor.service;

import com.haeahn.common.global.interceptor.dto.CustomHeaderDto;
import com.haeahn.common.global.utils.common.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class CustomHeaderService {
    private final CookieUtils cookieUtils;

    private final String sessionCookieName = "hsession";
    public String getSessionId(HttpServletRequest request, boolean newSessionId) {
        String sessionId = cookieUtils.getCookie(request, sessionCookieName);
        if (StringUtils.hasText(sessionId)) {
            return sessionId;
        }
        sessionId = request.getHeader("X-Session-Id");
        if (StringUtils.hasText(sessionId)) {
            return sessionId;
        }
        if (newSessionId) return UUID.randomUUID().toString().replace("-", "");
        else return null;
    }

    public Map<String, String> deviceInfo(String userAgent) {
        userAgent = userAgent.toLowerCase();
        Map<String, String> detectDevice = new ConcurrentHashMap<>();

        if (userAgent.contains("android")) {
            detectDevice.put("device", "android");
        } else if (userAgent.contains("iphone") || userAgent.contains("ipad") || userAgent.contains("ipod")) {
            detectDevice.put("device", "ios");
        } else {
            detectDevice.put("device", "desktop");
        }

        if (userAgent.contains("edge")) {
            detectDevice.put("browser", "edge");
        } else if (userAgent.contains("opr") || userAgent.contains("opera")) {
            detectDevice.put("browser", "opera");
        } else if (userAgent.contains("chrome")) {
            detectDevice.put("browser", "chrome");
        } else if (userAgent.contains("safari")) {
            detectDevice.put("browser", "safari");
        } else if (userAgent.contains("firefox")) {
            detectDevice.put("browser", "firefox");
        } else if (userAgent.contains("whale")) {
            detectDevice.put("browser", "whale");
        } else if (userAgent.contains("msie") || userAgent.contains("trident")) {
            detectDevice.put("browser", "ie");
        }
        return detectDevice;
    }

    public CustomHeaderDto getCustomHeader(HttpServletRequest request) {
        CustomHeaderDto header = new CustomHeaderDto();
        try {
            // IP 주소 추출 (프록시 환경 고려)
            String clientIp = extractClientIp(request);
            header.setClientIp(clientIp);

            // 디바이스 타입
            String deviceType = request.getHeader("X-Device-Type");
            if (StringUtils.hasText(deviceType)) {
                header.setDeviceType(deviceType);
            }

            // 세션 ID
            String sessionId = getSessionId(request, true);
            header.setSessionId(sessionId);

            // 디바이스 ID
            String deviceId = request.getHeader("X-Device-Id");
            if (StringUtils.hasText(deviceId)) {
                header.setDeviceId(deviceId);
            }

            // 앱 버전
            String appVersion = request.getHeader("X-App-Version");
            if (StringUtils.hasText(appVersion)) {
                header.setAppVersion(appVersion);
            }

            // User-Agent
            // User-Agent로 디바이스 타입 추정
            String customUserAgent = request.getHeader("X-Client-User-Agent");
            if (StringUtils.hasText(customUserAgent)) {
                header.setUserAgent(customUserAgent);
                var deviceInfo = deviceInfo(customUserAgent);
                header.setBrowserType(deviceInfo.get("device"));
            } else {
                String userAgent = request.getHeader("User-Agent");
                header.setUserAgent(userAgent != null ? userAgent : "");
                var deviceInfo = deviceInfo(userAgent);
                header.setBrowserType(deviceInfo.get("device"));
            }

            String userAgent = request.getHeader("X-Client-User-Agent");
            if (userAgent != null) header.setUserAgent(userAgent);
            else header.setUserAgent(request.getHeader("User-Agent") != null ? request.getHeader("User-Agent") : "");

            // Request ID (추적용)
            String requestId = request.getHeader("X-Request-Id");
            if (StringUtils.hasText(requestId)) {
                header.setRequestId(requestId);
            } else {
                header.setRequestId(UUID.randomUUID().toString());
            }

//            // 사용자 정보 (인증 후 설정)
//            String userId = request.getHeader("X-User-Id");
//            if (StringUtils.hasText(userId)) {
//                header.setUserId(userId);
//            }
//
//            String userRole = request.getHeader("X-User-Role");
//            if (StringUtils.hasText(userRole)) {
//                header.setUserRole(userRole);
//            }

            return header;

        } catch (Exception e) {
            System.err.println("Error extracting client info: " + e.getMessage());
            throw e;
        }

//    RequestCustomHeader header = new RequestCustomHeader();
//        try {
////            ipv6 > ipv4 = https://adjh54.tistory.com/442
//        String xfHeader = request.getHeader("X-Forwarded-For");
//        if (xfHeader != null && !xfHeader.isBlank()) {
////            return xfHeader.split(",")[0].trim();  // 첫 번째가 실제 클라이언트 IP
//            String[] parts = xfHeader.split(",");
//            if (parts.length > 0 && parts[0] != null && !parts[0].isBlank()) {
//                header.setClientIp(parts[0].trim());
//            } else {
//                header.setClientIp(request.getRemoteAddr());
//            }
//        } else {
//            header.setClientIp(request.getRemoteAddr());  // fallback: 직접 연결한 서버 IP (A 서버)
//        }
//
//        String deviceType = request.getHeader("X-Device-Type");
//        if (deviceType != null) header.setDeviceType(deviceType);
//

//        String deviceId = request.getHeader("X-Device-Id");
//        if (deviceId != null) header.setDeviceId(deviceId);
//
//        String appVersion = request.getHeader("X-App-Version");
//        if (appVersion != null) header.setAppVersion(appVersion);
//
//        String userAgent = request.getHeader("X-Client-User-Agent");
//        if (userAgent != null) header.setUserAgent(userAgent);
//        else header.setUserAgent(request.getHeader("User-Agent") != null ? request.getHeader("User-Agent") : "");
//
//        return header;
//    } catch (Exception e) {
//        e.printStackTrace();
//        throw e;
//    }

    }

    private String extractClientIp(HttpServletRequest request) {
//        X-Forwarded-For 헤더 확인 (프록시 환경)
//        return xfHeader.split(",")[0].trim();  // 첫 번째가 실제 클라이언트 IP
        String xfHeader = request.getHeader("X-Forwarded-For");
//        return xfHeader.split(",")[0].trim();  // 첫 번째가 실제 클라이언트 IP
        if (StringUtils.hasText(xfHeader)) {
            String[] parts = xfHeader.split(",");
            if (parts.length > 0 && StringUtils.hasText(parts[0])) {
                return parts[0].trim();
            }
        }

        // X-Real-IP 헤더 확인
        String realIp = request.getHeader("X-Real-IP");
        if (StringUtils.hasText(realIp)) {
            return realIp.trim();
        }

        // 직접 연결된 클라이언트 IP
        return request.getRemoteAddr();
    }
//    String xfHeader = request.getHeader("X-Forwarded-For");
//    if (xfHeader != null && !xfHeader.isBlank()) {
//        String[] parts = xfHeader.split(",");
//        if (parts.length > 0 && parts[0] != null && !parts[0].isBlank()) {
//            header.setClientIp(parts[0].trim());
//        } else {
//            header.setClientIp(request.getRemoteAddr());
//        }
//    } else {
//        header.setClientIp(request.getRemoteAddr());  // fallback: 직접 연결한 서버 IP (A 서버)
//    }

}
