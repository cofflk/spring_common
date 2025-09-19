package com.haeahn.common.global.storage.mssql.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "database.mssql")
@Getter
@Setter
public class DatabaseProperties {
//  application-database-[dev].yml > database.connection 
//  1. prefix = database 로 설정
//  2. 변수 이름을 connection 과 동일하게 설정
    @Getter
    private Map<String, Map<String, Property>> connection = new HashMap<>();
//    public Map<String, Map<String, Property>> getConnection() {
//        return connection;
//    }

    private PoolProperty configPool;
    public PoolProperty getPoolProperty() {
        return configPool;
    }

    @Getter
    @Setter
    public static class Property {
        private String driverClassName;
        private String url;
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public static class PoolProperty {
        private Boolean autoCommit;
        private int connectionTimeout;
        private int idleTimeout;
        private int keepaliveTime;
        private int maxLifetime;
        private int maximumPoolSize;
    }
}
