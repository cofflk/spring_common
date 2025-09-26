//package com.haeahn.common.global.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.graphql.execution.GraphQlSource;
//import org.springframework.web.servlet.function.RouterFunction;
//import org.springframework.web.servlet.function.RouterFunctions;
//import org.springframework.web.servlet.function.ServerResponse;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@Slf4j
//public class GraphQlConfig {
//    @Bean
//    public GraphQlSource graphQlSource() throws Exception {
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
////        Resource[] resources = resolver.getResources("classpath:graphql/code/**/*.graphqls");
//        Resource[] resources = resolver.getResources("classpath:graphql/mst-scheme.graphqls");
//        return GraphQlSource.schemaResourceBuilder()
//                .schemaResources(resources)
//                .build();
//// =========================
////
////        Resource[] resources = resolver.getResources("classpath:graphql/mst-scheme.graphqls");
////        // 'code' 스키마 리소스
////        Resource[] codeResources = resolver.getResources("classpath:graphql/code/**/*.graphqls");
////        // 'org' 스키마 리소스
////        Resource[] orgResources = resolver.getResources("classpath:graphql/org/**/*.graphqls");
////
////        log.info("mst-scheme resources: {}", resources.length);
////        log.info("code resources: {}", codeResources.length);
////        log.info("org resources: {}", orgResources.length);
////
////        // 두 리소스 배열을 하나의 리스트로 합치기
////        List<Resource> allResources = new ArrayList<>();
////        allResources.addAll(Arrays.asList(resources));
////        allResources.addAll(Arrays.asList(codeResources));
////        allResources.addAll(Arrays.asList(orgResources));
////
////        return GraphQlSource.schemaResourceBuilder()
////                .schemaResources(allResources.toArray(new Resource[0]))
////                .build();
//    }
//
//
//    @Bean
//    public RouterFunction<ServerResponse> graphiqlRouter() {
//        return RouterFunctions.resources("/graphiql/**", new ClassPathResource("graphiql/"));
//    }
//
////    /**
////     * /graphql/code 전용 GraphQlSource
////     * 간소화된 설정 (Spring Boot 3.4 호환)
////     */
////    @Bean
////    @Qualifier("graphQlSourceCode")
////    public GraphQlSource graphQlSourceCode() throws Exception {
////        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
////        Resource[] resources = resolver.getResources("classpath:graphql/code/**/*.graphqls");
////
////        return GraphQlSource.schemaResourceBuilder()
////                .schemaResources(resources)
////                .build();
////    }
////
////    /**
////     * /graphql/org 전용 GraphQlSource
////     * 간소화된 설정 (Spring Boot 3.4 호환)
////     */
////    @Bean
////    @Qualifier("graphQlSourceOrg")
////    public GraphQlSource graphQlSourceOrg() throws Exception {
////        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
////        Resource[] resources = resolver.getResources("classpath:graphql/org/**/*.graphqls");
////
////        return GraphQlSource.schemaResourceBuilder()
////                .schemaResources(resources)
////                .build();
////    }
////
////    /**
////     * GraphQL API 엔드포인트 라우팅 (API 백엔드 전용)
////     *
////     * 주의: 제시해주신 WebGraphQlHandler.builder(GraphQlSource) 방식은
////     * Spring Boot 3.4에서 API 변경으로 인해 동작하지 않습니다.
////     *
////     * 현재는 단순한 라우팅으로 구현하고, 실제 GraphQL 처리는
////     * @Controller + @QueryMapping 방식을 권장합니다.
////     */
////    @Bean
////    public RouterFunction<ServerResponse> graphQlRoutes() {
////        return RouterFunctions.route()
////                .POST("/graphql/code", request -> {
////                    log.info("GraphQL Code API request received");
////                    return ServerResponse.ok()
////                            .header("Content-Type", "application/json")
////                            .body("{\"message\":\"GraphQL Code endpoint configured - use @QueryMapping controllers\"}");
////                })
////                .POST("/graphql/org", request -> {
////                    log.info("GraphQL Org API request received");
////                    return ServerResponse.ok()
////                            .header("Content-Type", "application/json")
////                            .body("{\"message\":\"GraphQL Org endpoint configured - use @QueryMapping controllers\"}");
////                })
////                .build();
////    }
//}
