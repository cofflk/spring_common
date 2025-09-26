//package com.haeahn.common.global.config.bak;
//
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.graphql.ExecutionGraphQlService;
//import org.springframework.graphql.execution.DefaultExecutionGraphQlService;
//import org.springframework.graphql.execution.GraphQlSource;
//import org.springframework.graphql.server.WebGraphQlHandler;
//import org.springframework.graphql.server.webmvc.GraphQlHttpHandler;
//
//
///**
// * graphql 사용 시 /graphql/app1, /graphql/app2 와 같이 엔드포인트 분리 시 config 설정
// * - 단일 엔드포인트 사용 시 필요하지 않음
// *
// * ServletRegistrationBean : 복수 엔드포인트 + 스키마 분리
// * GraphQlSource : 단일 엔드포인트 + 스키마 분리
// */
//
//@Configuration
//public class GraphQlConfig {
//
//    /**
//     * App1용 GraphQL Source - code와 user 스키마를 포함
//     */
//    @Bean
//    public GraphQlSource codeGraphQlSource() {
//        Resource[] resources = {
//                new ClassPathResource("graphql/code/**/*.graphqls"),
////                new ClassPathResource("graphql/app1/search-user-scheme.graphqls")
//        };
//        return GraphQlSource.schemaResourceBuilder()
//                .schemaResources(resources)
//                .build();
//    }
//
//    /**
//     * App2용 GraphQL Source
//     */
//    @Bean
//    public GraphQlSource orgGraphQlSource() {
//        Resource[] resources = {
//                new ClassPathResource("graphql/org/**/*.graphqls"),
////                new ClassPathResource("graphql/app1/search-user-scheme.graphqls")
//        };
//        return GraphQlSource.schemaResourceBuilder()
//                .schemaResources(resources)
//                .build();
//    }
//
//
//    /**
//     * App1용 ExecutionGraphQlService
//     */
//    @Bean
//    public ExecutionGraphQlService codeExecutionGraphQlService(GraphQlSource codeGraphQlSource) {
//        return new DefaultExecutionGraphQlService(codeGraphQlSource);
//    }
//
//    /**
//     * App2용 ExecutionGraphQlService
//     */
//    @Bean
//    public ExecutionGraphQlService orgExecutionGraphQlService(GraphQlSource orgGraphQlSource) {
//        return new DefaultExecutionGraphQlService(orgGraphQlSource);
//    }
//
//    /**
//     * App1용 WebGraphQlHandler
//     */
//    @Bean
//    public WebGraphQlHandler codeWebGraphQlHandler(ExecutionGraphQlService codeExecutionGraphQlService) {
//        return WebGraphQlHandler.builder(codeExecutionGraphQlService).build();
//    }
//
//    /**
//     * App2용 WebGraphQlHandler
//     */
//    @Bean
//    public WebGraphQlHandler orgWebGraphQlHandler(ExecutionGraphQlService orgExecutionGraphQlService) {
//        return WebGraphQlHandler.builder(orgExecutionGraphQlService).build();
//    }
//
//    /**
//     * App1용 GraphQL HTTP Handler 등록 - /graphql/app1 엔드포인트
//     */
//    @Bean
//    public ServletRegistrationBean<GraphQlHttpHandler> app1GraphqlServlet(WebGraphQlHandler app1WebGraphQlHandler) {
//        GraphQlHttpHandler handler = new GraphQlHttpHandler(app1WebGraphQlHandler);
//        ServletRegistrationBean<GraphQlHttpHandler> registration =
//                new ServletRegistrationBean<>(handler, "/graphql/app1");
//        registration.setName("app1GraphqlServlet");
//        return registration;
//    }
//
//    /**
//     * App2용 GraphQL HTTP Handler 등록 - /graphql/app2 엔드포인트
//     */
//    @Bean
//    public ServletRegistrationBean<GraphQlHttpHandler> app2GraphqlServlet(WebGraphQlHandler app2WebGraphQlHandler) {
//        GraphQlHttpHandler handler = new GraphQlHttpHandler(app2WebGraphQlHandler);
//        ServletRegistrationBean<GraphQlHttpHandler> registration =
//                new ServletRegistrationBean<>(handler, "/graphql/app2");
//        registration.setName("app2GraphqlServlet");
//        return registration;
//    }
//
//
//
//
//
//
//
//
//
//// gemini
////    @Bean
////    public GraphQlSource codeGraphQlSource() {
////        return GraphQlSource.builder()
////                .schemaResources(r -> r.addPattern("classpath:graphql/code/**/*.graphqls"))
////                .build();
////    }
////    @Bean
////    public GraphQlSource app2GraphQlSource() {
////        return GraphQlSource.builder()
////                .schemaResources(r -> r.addPattern("classpath:graphql/app2/**/*.graphqls"))
////                .build();
////    }
//
//}
