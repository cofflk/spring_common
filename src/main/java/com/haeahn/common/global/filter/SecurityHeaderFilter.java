//package com.haeahn.common.global.filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//

// FE 서버에서 직접 설정
//public class SecurityHeaderFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        /**
//         * 클릭재킹 공격 방지
//         * DENY : 모든 iframe 로딩 금지
//         * SAMEORIGIN : 동일 도메인 내에서 접근 가능
//         * ALLOW-FROM {도메인} : 특정 도메인 접근 가능
//         */
//        res.setHeader("X-Frame-Options", "SAMEORIGIN");
//
//        /**
//         * MIME 타입 스니핑 방지
//         */
//        res.setHeader("X-Content-Type-Options", "nosniff");
//
//        /**
//         * HTTPS 강제 사용
//         * max-age=31536000 : 정책 유지 기간 = 31536000초 = 1년
//         * includeSubDomains : 서브 도메인 포함하여 요청
//         */
//        res.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
//
//        /**
//         * 리소스 로딩 제한, XSS 방지
//         * - 허가되지 않은 외부 JS 실행 차단, 인라인 스크립트 차단, 데이터 유출 방지
//         * none: 일치하지 않음
//         * self: 현재 출처와 일치, 하위 도메인 일치하지 않음
//         */
//        res.setHeader("Content-Security-Policy", "default-src 'self'");
//        res.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
//        res.setHeader("Permissions-Policy", "geolocation=(), camera=()");
////         필요하면 X-XSS-Protection 도 추가 가능
////        res.setHeader("X-XSS-Protection", "1; mode=block");
//
//        filterChain.doFilter(request, response);
//    }
//}
