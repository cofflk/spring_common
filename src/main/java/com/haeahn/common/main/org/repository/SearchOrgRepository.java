package com.haeahn.common.main.org.repository;

import com.haeahn.common.global.storage.mssql.service.DatabaseService;
import com.haeahn.common.main.org.dto.SearchOrgUserDto;
import com.haeahn.common.main.org.payload.request.ReqSearchOrgUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@GraphQlRepository
@Slf4j(topic="ErrorLog")
@RequiredArgsConstructor
public class SearchOrgRepository {
    private final DatabaseService dt;

    @SuppressWarnings("unchecked")
    private <T> List<T> getList(Map<String, Object> map, String key) {
        return (List<T>) map.getOrDefault(key, Collections.emptyList());
    }

    public Map<String, List<SearchOrgUserDto>> searchOrgUser(ReqSearchOrgUser reqSearchOrgUser, List<String> resultNames) {
        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("EMP_NO", reqSearchOrgUser.getEmpNo())
                    .addValue("KEYWORD", reqSearchOrgUser.getKeyword())
                    .addValue("SVC_NM", reqSearchOrgUser.getServiceName())
                    .addValue("OPT_TYPE", reqSearchOrgUser.getOptType())
                    .addValue("OPT_PAGE_SIZE", reqSearchOrgUser.getOptPageSize())
                    .addValue("OPT_PAGE", reqSearchOrgUser.getOptPage());
//            Object result = dt.result("org", "USP_GET_USER_LIST", params);
//            @SuppressWarnings("unchecked")
//            List<Map<String, Object>> listResult = (List<Map<String, Object>>) result;
//            return dt.mapperToDto(listResult, SearchOrgUserDto.class);

            // case 1)
//            List<String> resultNames = Arrays.asList("BOOK", "RESULT");
//            Map<String, Object> result = dt.resultWithNames("org", "USP_GET_USER_LIST", params, resultNames);
//            return Map.of(
//                    "BOOK", dt.mapperToDto(getList(result, "BOOK"), SearchOrgUserDto.class),
//                    "RESULT", dt.mapperToDto(getList(result, "RESULT"), SearchOrgUserDto.class)
//            );
            Map<String, Object> result = dt.resultWithNames("org", "USP_GET_USER_LIST", params, resultNames);
            return resultNames.stream()
                    .collect(Collectors.toMap(
                            name -> name,
                            name -> dt.mapperToDto(getList(result, name), SearchOrgUserDto.class)
                    ));
        } catch (Exception e) {
            log.error("[SearchOrgRepository/searchOrgUser] ERROR empNo:{},keyword:{},optType:{},optPageSize:{},optPage:{},error:{},",
                    reqSearchOrgUser.getEmpNo(), reqSearchOrgUser.getKeyword(), reqSearchOrgUser.getOptType(),
                    reqSearchOrgUser.getOptPageSize(), reqSearchOrgUser.getOptPage(), e.getMessage());
            throw e;
        }
    }
}
