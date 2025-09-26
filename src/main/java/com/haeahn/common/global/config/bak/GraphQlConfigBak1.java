//package com.haeahn.common.global.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.graphql.ExecutionGraphQlService;
//import org.springframework.graphql.execution.GraphQlSource;
//import org.springframework.graphql.server.WebGraphQlHandler;
//import org.springframework.graphql.server.webmvc.GraphQlHttpHandler;
//import org.springframework.web.servlet.function.RouterFunction;
//import org.springframework.web.servlet.function.RouterFunctions;
//import org.springframework.web.servlet.function.ServerResponse;
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
//public class GraphQlConfigBak1 {
//
//    @Bean
//    public GraphQlSource graphQlSourceCode() throws Exception {
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = resolver.getResources("classpath:graphql/code/**/*.graphqls");
//
//        return GraphQlSource.schemaResourceBuilder()
//                .schemaResources(resources)
//                .build();
////        return GraphQlSource.schemaResourceBuilder()
////                .schemaResources(
////                        new ClassPathResource("classpath:graphql/code/**/*.graphqls")
//////                        new ClassPathResource("graphql/code/**/*.graphqls")
//////                        new ClassPathResource("graphql/app1/order.graphqls"),
////                )
////                .build();
//    }
//
//    @Bean
//    public GraphQlSource graphQlSourceOrg() throws Exception {
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = resolver.getResources("classpath:graphql/org/**/*.graphqls");
//
//        return GraphQlSource.schemaResourceBuilder()
//                .schemaResources(resources)
//                .build();
//
////        return GraphQlSource.schemaResourceBuilder()
////                .schemaResources(
////                        new ClassPathResource("classpath:graphql/org/**/*.graphqls")
//////                        new ClassPathResource("graphql/app1/order.graphqls"),
////                )
////                .build();
//    }
//
//
//    // -------------------- ExecutionGraphQlService Beans --------------------
//    @Bean
//    public ExecutionGraphQlService codeExecutionGraphQlService(@Qualifier("graphQlSourceCode") GraphQlSource graphQlSourceCode) {
//        return new ExecutionGraphQlService(graphQlSourceCode);
//    }
//
//    @Bean
//    public ExecutionGraphQlService orgExecutionGraphQlService(@Qualifier("graphQlSourceOrg") GraphQlSource graphQlSourceOrg) {
//        return new ExecutionGraphQlService(graphQlSourceOrg);
//    }
//
//    // -------------------- WebGraphQlHandler Beans --------------------
//    @Bean
//    public WebGraphQlHandler codeHandler(@Qualifier("graphQlSourceCode") GraphQlSource source) {
//        return WebGraphQlHandler.builder((ExecutionGraphQlService) source).build();
//    }
//
//    @Bean
//    public WebGraphQlHandler orgHandler(@Qualifier("graphQlSourceOrg") GraphQlSource source) {
//        return WebGraphQlHandler.builder((ExecutionGraphQlService) source).build();
//    }
//
//    // -------------------- RouterFunction Bean --------------------
//    @Bean
//    public RouterFunction<ServerResponse> graphQlRoutes(
//            @Qualifier("codeHandler") WebGraphQlHandler codeHandler,
//            @Qualifier("orgHandler") WebGraphQlHandler orgHandler
//    ) {
//        return RouterFunctions.route()
//                .POST("/graphql/code", new GraphQlHttpHandler(codeHandler)::handleRequest)
//                .POST("/graphql/org", new GraphQlHttpHandler(orgHandler)::handleRequest)
//                .build();
//    }
//}
