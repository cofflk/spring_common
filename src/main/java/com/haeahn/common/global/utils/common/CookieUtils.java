package com.haeahn.common.global.utils.common;

import com.haeahn.common.global.config.properties.PropertiesReader;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieUtils {
    private final PropertiesReader propertiesReader;

    private static final String COOKIE_DOMAIN = "localhost"; // "." + propertiesReader.getProperty("server.domain")
    private final int EXPIRATION = -1;
    private final boolean HTTP_ONLY = false;
    private final boolean SECURE = false;
    private final String SAME_SITE = "Strict";

    public String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue(); // 해당 이름의 쿠키 존재
                }
            }
        }
        return ""; // 쿠키가 없거나 해당 이름의 쿠키 없음
    }

    public boolean hasCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return true; // 해당 이름의 쿠키 존재
                }
            }
        }
        return false; // 쿠키가 없거나 해당 이름의 쿠키 없음
    }


    public void addCookie(
            HttpServletResponse response,
            String cookieName, String cookieVal,
            String domain, String path, int expirationSecond,
            boolean httpOnly, boolean secure, String sameSite
    ) {
        Cookie cookie = new Cookie(cookieName, cookieVal);
        cookie.setDomain((domain == null || domain.isBlank()) ?  COOKIE_DOMAIN : domain);
        cookie.setPath((path == null || path.isBlank()) ? "/" : path);
        cookie.setMaxAge(expirationSecond);
        cookie.setHttpOnly(httpOnly);
        cookie.setSecure(secure);
        cookie.setAttribute("SameSite", sameSite);
        response.addCookie(cookie);
    }

    public void addCookie(
            HttpServletResponse response,
            String cookieName, String cookieVal,
            String domain, String path, int expirationSecond
    ) {
        addCookie(response, cookieName, cookieVal, domain, path, expirationSecond, HTTP_ONLY, SECURE, SAME_SITE);
    }

    public void addCookie(HttpServletResponse response, String cookieName, String cookieVal, int expirationSecond) {
        addCookie(response, cookieName, cookieVal, null, null, expirationSecond, HTTP_ONLY, SECURE, SAME_SITE);
    }

    /**
     * 세션 쿠키 생성
     */
    public void addCookie(HttpServletResponse response, String cookieName, String cookieVal) {
        addCookie(response, cookieName, cookieVal, null, null, -1, HTTP_ONLY, SECURE, SAME_SITE);
    }


    public void deleteCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
