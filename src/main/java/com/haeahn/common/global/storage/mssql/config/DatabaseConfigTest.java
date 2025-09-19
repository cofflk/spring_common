//package com.haeahn.svr_work.global.db.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//class DatabaseConfigTest {
//
//    @Autowired
//    private String ip;
//    private String port;
//    private String username;
//    private String password;
//    private String dbName;
//
//    public String getConnection (String server, String dbName) {
//        String keyProperty = String.format("string.database.%s.key", provider);
//        return env.getProperty(keyProperty);
//    }
//
//    public String getApiUrl(String provider) {
//        String urlProperty = String.format("service.%s.url", provider);
//        return env.getProperty(urlProperty);
//    }
//}
//
//
//
//
//
//
//// 1. 단일 설정을 가져오는 방법
//
////// appsettings.yml
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.stereotype.Component;
////
////@Component
////class DatabaseConfig {
////
////    @Value("${app.name}")
////    private String databaseName;
////
////    protected String getValue(String path) {
////        Assertions.assertEquals(config.getPath(), path);
////    }
////}
//
//
//
//// 2. 그룹으로 가져오는 방법 - prefix 사용
////
////import org.springframework.boot.context.properties.ConfigurationProperties;
////import org.springframework.stereotype.Component;
//
////@Component
////@ConfigurationProperties(prefix = "spring.database")
////
////class DatabaseConfig {
////
////    private String ip;
////    private String port;
////    private String username;
////    private String password;
////    private String dbName;
////
////    protected String getConnection (String server, String dbName) {
////        this.ip =
////    }
////}
////
