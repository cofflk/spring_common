package com.haeahn.common.main.org.repository;

import com.haeahn.common.global.storage.mssql.service.DatabaseService;
import com.haeahn.common.main.org.dto.SearchOrgUserDto;
import com.haeahn.common.main.org.payload.ReqSearchOrgUser;
import com.haeahn.common.main.org.payload.ResSearchOrgUser;
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
public class SearchOrgRepository {
    private final DatabaseService dt;

    public List<SearchOrgUserDto> searchOrgUser(ReqSearchOrgUser reqSearchOrgUser) {
        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("EMP_NO", reqSearchOrgUser.getEmpNo())
                    .addValue("KEYWORD", reqSearchOrgUser.getKeyword())
                    .addValue("OPT_TYPE", reqSearchOrgUser.getOptType())
                    .addValue("OPT_PAGE_SIZE", reqSearchOrgUser.getOptPageSize())
                    .addValue("OPT_PAGE", reqSearchOrgUser.getOptPage());
//            Object result = dt.result("org", "up_Get_User_LoginCheck", params);
            Object result = dt.result("org", "USP_GET_USER_LIST", params);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result;
            return dt.mapperToDto(listResult, SearchOrgUserDto.class);
        } catch (Exception e) {
            log.error("[SearchOrgRepository/searchOrgUser] ERROR empNo:{},keyword:{},optType:{},optPageSize:{},optPage:{},error:{},",
                    reqSearchOrgUser.getEmpNo(), reqSearchOrgUser.getKeyword(), reqSearchOrgUser.getOptType(),
                    reqSearchOrgUser.getOptPageSize(), reqSearchOrgUser.getOptPage(), e.getMessage());
            throw e;
        }
    }
}
