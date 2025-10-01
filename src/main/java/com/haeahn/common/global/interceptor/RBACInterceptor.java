package com.haeahn.common.global.interceptor;

import com.haeahn.common.global.annotation.LogAccess;
import com.haeahn.common.global.config.properties.AuthTokenProperties;
import com.haeahn.common.global.dto.CustomHeaderDto;
import com.haeahn.common.global.threadlocal.CustomHeaderContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.*;

//Role Based Access Control
@Component
@RequiredArgsConstructor
@Slf4j(topic="ErrorLog")
public class RBACInterceptor implements HandlerInterceptor {
    private final AuthTokenProperties authTokenProperties;

    // 하드코딩: 허용 Role 목록
    private static final Set<String> ALLOWED_ROLES = new HashSet<>(Set.of(
            "ROLE_SYSTEM",
            "ROLE_COMM_ADMIN"
    ));

    @Override
    @LogAccess(value="역할 기반 접근 관리")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CustomHeaderDto clientInfo = CustomHeaderContext.get();
        String userRole = clientInfo.getUserRole();

        Set<String> adminOnlyUrls = new HashSet<>(authTokenProperties.getAdminOnlyUrls());

        String requestUrl = request.getRequestURI();
        if (adminOnlyUrls.contains(requestUrl.toLowerCase())) {
            // 쉼표로 분리 → Role 리스트 생성
            List<String> roles = Arrays.stream(userRole.split(","))
                    .map(String::trim)
                    .map(r -> r.replace("ROLE_", "")) // ROLE_ 제거
                    .toList();

            // 허용 Role 체크
            boolean hasAllowedRole = roles.stream().anyMatch(ALLOWED_ROLES::contains);

//            if (!roles.contains("ADMIN")) { // ADMIN 역할이 없으면 접근 차단
            if (!hasAllowedRole) { // ADMIN 역할이 없으면 접근 차단
                log.error("[RBACInterceptor] ERROR/ATTEMPT SWAGGER URL, empNo:{},requestUrl:{},", clientInfo.getUserId(), requestUrl);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Access Denied: ADMIN role required");
                return false;
            }
            return true; // ADMIN이면 허용
        }
        // 다른 경로는 authFilter 에서 전처리 적용
        return true;
    }
}
