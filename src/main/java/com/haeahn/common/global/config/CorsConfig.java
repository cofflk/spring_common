package com.haeahn.common.global.config;

import com.haeahn.common.global.config.properties.CorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {
    private final CorsProperties corsProperties;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // 적용할 경로 설정
//                .allowedOrigins("http://localhost:3000") // 허용할 출처
//                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 메서드
//                .allowedHeaders("*") // 허용할 헤더
//                .allowCredentials(true); // 인증 정보 허용 여부

        registry.addMapping("/**") // 적용할 경로 설정
                .allowedOriginPatterns(corsProperties.getAllowedOrigins().toArray(new String[0]))
                .allowedMethods(corsProperties.getAllowedMethods().toArray(new String[0]))
                .allowedHeaders(corsProperties.getAllowedHeaders().toArray(new String[0]))
                .allowCredentials(true) // 인증 정보 허용 여부
                .maxAge(3600);

        // graphiql 추가
        registry.addMapping("/graphiql") // 명시적으로 추가
                .allowedOriginPatterns("*")
                .allowedMethods(corsProperties.getAllowedMethods().toArray(new String[0]))
                .allowedHeaders(corsProperties.getAllowedHeaders().toArray(new String[0]))
                .allowCredentials(true)
                .maxAge(3600);
    }
}
