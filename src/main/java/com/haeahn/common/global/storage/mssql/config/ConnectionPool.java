package com.haeahn.common.global.storage.mssql.config;//package com.haeahn.svr_work.example.A002_dbConnection.common;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
//@MapperScan(value="mapper 파일 경로", sqlS)
public class ConnectionPool {
    private final DatabaseProperties properties;
    private final Map<String, DataSource> cache = new ConcurrentHashMap<>();

    public DataSource getDataSource(String server, String db) {
        return cache.computeIfAbsent(db, k -> createDataSource(server, db));
    }

    private DataSource createDataSource(String server, String dbName) {
        DatabaseProperties.Property config = Optional.ofNullable(
                        properties.getConnection().get(server))
                .map(m -> m.get(dbName))
                .orElseThrow(() -> new IllegalArgumentException("Invalid server/db: " + dbName));
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(config.getUrl());
        ds.setUsername(config.getUsername());
        ds.setPassword(config.getPassword());

        ds.setAutoCommit(properties.getPoolProperty().getAutoCommit());
        ds.setConnectionTimeout(properties.getPoolProperty().getConnectionTimeout());
        ds.setIdleTimeout(properties.getPoolProperty().getIdleTimeout());
        ds.setKeepaliveTime(properties.getPoolProperty().getKeepaliveTime());
        ds.setMaxLifetime(properties.getPoolProperty().getMaxLifetime());
        ds.setMaximumPoolSize(properties.getPoolProperty().getMaximumPoolSize());
        ds.setPoolName(dbName + "_HikariPool"); // 풀 이름

        return ds;
    }
}



