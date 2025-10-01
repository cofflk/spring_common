package com.haeahn.common.global.filter;

import com.haeahn.common.global.threadlocal.CustomHeaderContext;
import com.haeahn.common.global.dto.CustomHeaderDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class MdcFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // CustomHeaderContext에서 정보 가져오기
            CustomHeaderDto header = CustomHeaderContext.get();

//            // MDC에 컨텍스트 정보 설정
//            String requestId = header.getRequestId();
//            if (requestId == null || requestId.isEmpty()) {
//                requestId = UUID.randomUUID().toString().substring(0, 8);
//            }
//            String userId = header.getUserId(); // CustomHeaderDto에 userId 필드가 있다고 가정

            // MDC 설정
            MDC.put("requestId", header.getRequestId());
            MDC.put("clientIp", header.getClientIp());
            MDC.put("deviceType", header.getDeviceType());
            MDC.put("sessionId", header.getSessionId());
            MDC.put("deviceId", header.getDeviceId());
            MDC.put("deviceOs", header.getDeviceOs());
            MDC.put("appVersion", header.getAppVersion());
            MDC.put("userAgent", header.getUserAgent());
            MDC.put("browserType", header.getBrowserType());
            MDC.put("method", request.getMethod());

//            MDC.put("clientIp", clientIp != null ? clientIp : "-");
//            MDC.put("userId", userId != null ? userId : "-");
//            MDC.put("url", request.getRequestURI());
//            MDC.put("method", request.getMethod());
//            MDC.put("userAgent", header.getUserAgent());
            String msg = String.format("deviceType:%s,sessionId:%s,deviceId:%s,deviceOs:%s,appVersion:%s,userAgent:%s,browserType:%s,",
                    header.getDeviceType(),
                    header.getSessionId(),
                    header.getDeviceId(),
                    header.getDeviceOs(),
                    header.getAppVersion(),
                    header.getUserAgent(),
                    header.getBrowserType()
            );
            log.info("[MDC] {}", msg);

            filterChain.doFilter(request, response);

        } finally {
            // MDC 컨텍스트 정리
            MDC.clear();
        }

    }
}
