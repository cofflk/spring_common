package com.haeahn.common.global.interceptor;

import com.haeahn.common.global.annotation.LogAccess;
import com.haeahn.common.global.interceptor.context.AuthContext;
import com.haeahn.common.global.interceptor.dto.CustomHeaderDto;
import com.haeahn.common.global.interceptor.service.CustomHeaderService;
import com.haeahn.common.global.interceptor.context.CustomHeaderContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
//@ConfigurationProperties(prefix = "logging.file")
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    private final CustomHeaderService customHeaderService;

    @Override
    @LogAccess(value="인증 인터셉터")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 세션 여부 확인
        HttpSession session = request.getSession();
        Object principal = session.getAttribute("principal");

        // threadlocal 존재 여부 확인
        if (AuthContext.hasContext()) {
            return true;
        }
        
//        필터에서 적용 -> MDC 설정에 필요
//        // 헤더 정보 조회
//        CustomHeaderDto customHeaderDto = customHeaderService.getCustomHeader(request);
//        CustomHeaderContext.set(customHeaderDto);
//        // Request 속성에 저장 (컨트롤러에서 접근 가능)
//        request.setAttribute("clientInfo", customHeaderDto);
//        log.info("Request started - IP: {}, Device: {}, User: {}",customHeaderDto.getClientIp(),customHeaderDto.getDeviceType(),customHeaderDto.getUserId());

        CustomHeaderDto header = CustomHeaderContext.get();
        log.info("Request started - IP: {}, Device: {}, User: {}",
                header.getClientIp(),
                header.getDeviceType(),
                header.getUserId());
        
//        API 서버에 로그인 인증 필요

//        실패 시 > return false
//        통과 시 > return true;
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // MDC 정리
        org.slf4j.MDC.clear();

        CustomHeaderContext.clear();

        CustomHeaderDto clientInfo = (CustomHeaderDto) request.getAttribute("clientInfo");
        if (clientInfo != null) {
            log.info("Request completed - IP: {}, Status: {}",
                    clientInfo.getClientIp(),
                    response.getStatus());
        }
    }

    /**
     * MDC 설정을 위한 헬퍼 메서드
     */
    private void setupMDC(CustomHeaderDto clientInfo) {
        org.slf4j.MDC.put("requestId", clientInfo.getRequestId());
        org.slf4j.MDC.put("clientIp", clientInfo.getClientIp());
        org.slf4j.MDC.put("userId", clientInfo.getUserId() != null ? clientInfo.getUserId() : "anonymous");
    }
}
