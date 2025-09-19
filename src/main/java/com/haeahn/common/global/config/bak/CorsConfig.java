//package com.haeahn.common.global.config.cors;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//

///**
// * 1) @Bean : cors, interceptor 등을 같은 webMvcConfigurer 클래스에서 설정필요
// * 2) implements WebMvcConfigurer: 개별 클래스에서 분리
// */
//@Configuration
//public class CorsConfig {
//    private final CorsProperties corsProperties;
//
//    public CorsConfig(CorsProperties corsProperties) {
//        this.corsProperties = corsProperties;
//    }
//
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // 적용할 경로 설정
//                        .allowedOriginPatterns(corsProperties.getAllowedOrigins().toArray(new String[0]))
//                        .allowedMethods(corsProperties.getAllowedMethods().toArray(new String[0]))
//                        .allowedHeaders(corsProperties.getAllowedHeaders().toArray(new String[0]))
//                        .allowCredentials(true)
//                        .maxAge(3600);
//            }
//        };
//    }
//}
