package com.haeahn.common.main.code.repository;

import com.haeahn.common.global.storage.mssql.service.DatabaseService;
import com.haeahn.common.main.code.payload.request.ReqErpCommCode;
import com.haeahn.common.main.code.payload.request.ReqErpHrCode;
import com.haeahn.common.main.code.dto.ErpCommCode;
import com.haeahn.common.main.code.dto.ErpHrCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@GraphQlRepository
@Slf4j(topic="ErrorLog")
@RequiredArgsConstructor
public class CodeRepository {
    private final DatabaseService dt;

    public List<ErpCommCode> findErpCommCode(ReqErpCommCode reqErpCommCode) {
        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("GRP_CD", "HR")
                    .addValue("MODL_CD", reqErpCommCode.getCdSystem())
                    .addValue("DECL_CD", reqErpCommCode.getCdType())
                    .addValue("IMPL_CD", reqErpCommCode.getCdCode())
                    .addValue("IMPL_NM", reqErpCommCode.getDsCode());
//            Object result = dt.result("org", "up_Get_User_LoginCheck", params);
            Object result = dt.result("common", "USP_GET_ERP_CODE_LIST", params);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result;
            return dt.mapperToDto(listResult, ErpCommCode.class);
        } catch (Exception e) {
            log.error("[CodeRepository/findErpCommCode] ERROR cdSystem:{},cdType:{},cdCode:{},dsCode:{},error:{},",
                    reqErpCommCode.getCdSystem(), reqErpCommCode.getCdType(), reqErpCommCode.getCdCode(), reqErpCommCode.getDsCode(), e.getMessage());
            throw e;
        }
    }

    public List<ErpHrCode> findErpHrCode(ReqErpHrCode reqErpHrCode) {
        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("GRP_CD", "HR")
                    .addValue("MODL_CD", reqErpHrCode.getCdPrefix())
                    .addValue("DECL_CD", reqErpHrCode.getCdUpcode())
                    .addValue("IMPL_CD", reqErpHrCode.getCdCode())
                    .addValue("IMPL_NM", reqErpHrCode.getDsCode());
//            Object result = dt.result("org", "up_Get_User_LoginCheck", params);
            Object result = dt.result("common", "USP_GET_ERP_CODE_LIST", params);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result;
            return dt.mapperToDto(listResult, ErpHrCode.class);
        } catch (Exception e) {
            log.error("[CodeRepository/findErpHrCode] ERROR cdPrefix:{},cdUpcode:{},cdCode:{},dsCode:{},error:{},",
                    reqErpHrCode.getCdPrefix(), reqErpHrCode.getCdUpcode(), reqErpHrCode.getCdCode(), reqErpHrCode.getDsCode(), e.getMessage());
            throw e;
        }
    }
}
