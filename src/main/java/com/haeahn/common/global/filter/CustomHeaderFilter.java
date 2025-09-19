package com.haeahn.common.global.filter;

import com.haeahn.common.global.interceptor.context.CustomHeaderContext;
import com.haeahn.common.global.interceptor.dto.CustomHeaderDto;
import com.haeahn.common.global.interceptor.service.CustomHeaderService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomHeaderFilter extends OncePerRequestFilter {
    private final CustomHeaderService customHeaderService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // 클라이언트 정보 추출 >ThreadLocal에 저장
            CustomHeaderDto clientInfo = customHeaderService.getCustomHeader(request);

            // Request 속성에 저장 (필요한 경우)
            request.setAttribute("clientInfo", clientInfo);

            // ThreadLocal에 저장하여 요청 전체에서 재사용
            CustomHeaderContext.set(clientInfo);

            String sessionId = clientInfo.getSessionId();
            if ("desktop".equalsIgnoreCase(clientInfo.getDeviceType()) && (sessionId == null || sessionId.isBlank())) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"error\": \"Missing X-Session-Id header\"}");
                return; // 에러 응답 후 종료
            }

            filterChain.doFilter(request, response);
        } finally {
            CustomHeaderContext.clear();
        }
    }
}