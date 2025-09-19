//package com.haeahn.common.global.storage.cache.redis.reference;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@ConfigurationProperties(prefix = "redis.templates")
//@Getter
//@Setter
//public class MultiRedisConfig {
//    private Map<String, RedisProperties> instances = new HashMap<>();
//
//    public Map<String, RedisProperties> getInstance() {
//        return instances;
//    }
//
//    public RedisProperties getProperty(String key) {
//        return instances.get(key);
//    }
//
//    public void setInstance(Map<String, RedisProperties> instances) {
//        this.instances = instances;
//    }
//
//    @Getter
//    @Setter
//    public static class RedisProperties {
//        private String host;
//        private int port;
//        private int database;
//    }
//
////    private String host = "localhost";
////    private int port = 6379;
////    private String password = "1234";
////    private int database = 0; // 기본 database
//
//
//}
