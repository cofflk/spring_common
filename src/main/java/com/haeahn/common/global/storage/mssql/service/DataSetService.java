//package com.haeahn.common.global.storage.mssql.service;
//
//import com.haeahn.common.global.storage.mssql.config.ConnectionPool;
//import com.haeahn.common.global.storage.mssql.repository.ProcedureRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.stereotype.Service;
//
//import javax.sql.DataSource;
//import java.util.Map;
//
//@Service
//public class DataSetService {
//
//    @Autowired
//    ConnectionPool conn;
//    @Autowired
//    ProcedureRepository proc;
//
//    public Map<String, Object> result(String server, String db, String procName, SqlParameterSource params) {
//        DataSource ds = conn.getDataSource(server, db);
//        return proc.executeProcedure(ds, procName, params);
//    }
//
//    public Map<String, Object> result(String db, String procName, SqlParameterSource params) {
//        DataSource ds = conn.getDataSource("default", db);
//        return proc.executeProcedure(ds, procName, params);
//    }
//}
