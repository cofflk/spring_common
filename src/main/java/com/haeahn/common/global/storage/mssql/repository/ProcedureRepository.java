package com.haeahn.common.global.storage.mssql.repository;

import com.haeahn.common.global.storage.mssql.config.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class ProcedureRepository {

    @Autowired
    private ConnectionPool conn;

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> executeProcedure(DataSource ds, String procedureName, Map<String, ?> params) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(ds)
                .withProcedureName(procedureName);
        return jdbcCall.execute(params);
    }

    public Map<String, Object> executeProcedure(DataSource ds, String procedureName, SqlParameterSource params) {
//        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(ds)
                .withProcedureName(procedureName);
        if (params == null) return jdbcCall.execute();
        return jdbcCall.execute(params);
    }
}
