//package com.haeahn.common.global.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.graphql.server.WebGraphQlHandler;
//import org.springframework.graphql.server.webmvc.GraphQlHttpHandler;
//import org.springframework.web.servlet.function.RouterFunction;
//import org.springframework.web.servlet.function.RouterFunctions;
//import org.springframework.web.servlet.function.ServerResponse;
//
//@Configuration
////@RequiredArgsConstructor
//public class GraphQlRouterConfig {
//    private final WebGraphQlHandler codeHandler;
//    private final WebGraphQlHandler orgHandler;
//
//    public GraphQlRouterConfig(
//            @Qualifier("codeHandler") WebGraphQlHandler codeHandler,
//            @Qualifier("orgHandler") WebGraphQlHandler orgHandler
//    ) {
//        this.codeHandler = codeHandler;
//        this.orgHandler = orgHandler;
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> graphQlRoutes() {
//        return RouterFunctions.route()
//                .POST("/graphql/app1", new GraphQlHttpHandler(app1Handler)::handleRequest)
//                .POST("/graphql/app2", new GraphQlHttpHandler(app2Handler)::handleRequest)
//                .build();
//    }
//}
