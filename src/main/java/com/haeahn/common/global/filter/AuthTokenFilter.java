package com.haeahn.common.global.filter;

import com.haeahn.common.global.config.properties.AuthTokenProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
    private final WebClient webClient;
    private final AuthTokenProperties authTokenProperties;

    public AuthTokenFilter(WebClient.Builder builder,
                           AuthTokenProperties authTokenProperties) {
        this.webClient = builder.baseUrl("https://hubnx.haeahn.com").build();
        this.authTokenProperties = authTokenProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Set<String> whitelistUrl = new HashSet<>(authTokenProperties.getWhitelistUrls());

        // 1. 쿠키 가져오기
        String accessToken = getCookieValue(req, "hac");
        String refreshToken = getCookieValue(req, "hrf");
        String loginPage = "/login"; // 로그인 페이지

        String requestUrl = request.getRequestURI();
        if (whitelistUrl.contains(requestUrl.toLowerCase())) {
            log.info("[AuthTokenFilter] START/WHITELIST, url:{},requestURI:{}", requestUrl, request.getRequestURI());
            filterChain.doFilter(request,response);
            return;
        }
        else {
            log.info("[AuthTokenFilter] START, url:{},requestURI:{}", requestUrl, request.getRequestURI());
        }

        try {
            if (accessToken == null || Boolean.FALSE.equals(validateAccessToken(accessToken).block())) {
                // Access 토큰 없거나 유효하지 않으면 재발급 시도
                boolean reissued = (refreshToken != null) && Boolean.TRUE.equals(reissueAccessToken(refreshToken, res).block());
                if (!reissued) {
                    // 재발급 실패 → 로그인 페이지로 리다이렉트
                    res.sendRedirect(loginPage);
                    return;
                }
            }

            // 유효한 경우 다음 필터/컨트롤러로 진행
            filterChain.doFilter(request,response);

        } catch (Exception e) {
            log.error("[AuthTokenFilter] EXCEPTION, error:{}", e.getMessage());
//            res.sendRedirect("/login");
        }

        String result = webClient.get()
                .uri("/api/me")
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 동기 호출

        System.out.println("API Response: " + result);

        filterChain.doFilter(request, response);

    }

    private String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return null;
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals(name)) return c.getValue();
        }
        return null;
    }

    // Access 토큰 검증
    private Mono<Boolean> validateAccessToken(String token) {
        return webClient.get()
                .uri("/be/auth/api/me")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> body.contains("\"code\":\"OK\""))
                .onErrorReturn(false);
    }

    // Access 토큰 재발급
    private Mono<Boolean> reissueAccessToken(String refreshToken, HttpServletResponse response) {
        return webClient.post()
                .uri("/be/auth/reissue/access")
                .header("Authorization", "Bearer " + refreshToken)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        // Authorization 헤더 읽기
                        String newToken = clientResponse.headers()
                                .asHttpHeaders()
                                .getFirst(HttpHeaders.AUTHORIZATION);

                        if (newToken != null && newToken.startsWith("Bearer ")) {
                            newToken = newToken.substring("Bearer ".length());

                            // 쿠키로 저장
                            Cookie newAccessCookie = new Cookie("hac", newToken);
                            newAccessCookie.setDomain(".haeahn.com");  // 도메인 지정
                            newAccessCookie.setPath("/");              // 모든 경로
                            newAccessCookie.setHttpOnly(true);
                            newAccessCookie.setSecure(true);
                            newAccessCookie.setMaxAge(Integer.parseInt(authTokenProperties.getExpiration().get("access")) * 60);

                            response.addCookie(newAccessCookie);
                            return Mono.just(true);
                        }
                    }
                    return Mono.just(false);
                });

//        return webClient.post()
//                .uri("/be/auth/api/reissue/access")
//                .header("Authorization", "Bearer " + refreshToken)
//                .retrieve()
//                .bodyToMono(String.class)
//                .map(body -> {
//                    // 재발급 성공 시 쿠키 갱신
//                    Cookie newAccessCookie = new Cookie("hac", extractTokenFromBody(body));
//                    newAccessCookie.setDomain(".haeahn.com");
//                    newAccessCookie.setPath("/");
//                    newAccessCookie.setHttpOnly(true);
//                    newAccessCookie.setSecure(true);
//                    newAccessCookie.setMaxAge(Integer.parseInt(authTokenProperties.getExpiration().get("access")) * 60);
//                    response.addCookie(newAccessCookie);
//                    return true;
//                })
//                .onErrorReturn(false);

    }
}
