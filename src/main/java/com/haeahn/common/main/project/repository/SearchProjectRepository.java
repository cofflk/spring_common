package com.haeahn.common.main.project.repository;

import com.haeahn.common.global.storage.mssql.service.DatabaseService;
import com.haeahn.common.main.project.dto.SearchProjectDto;
import com.haeahn.common.main.project.payload.request.ReqSearchProject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@GraphQlRepository
@Slf4j(topic="ErrorLog")
@RequiredArgsConstructor
public class SearchProjectRepository {
    private final DatabaseService dt;

    @SuppressWarnings("unchecked")
    private <T> List<T> getList(Map<String, Object> map, String key) {
        return (List<T>) map.getOrDefault(key, Collections.emptyList());
    }

    public Map<String, List<SearchProjectDto>> searchProject(ReqSearchProject reqSearchProject, List<String> resultNames) {
        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("EMP_NO", reqSearchProject.getEmpNo())
                    .addValue("KEYWORD", reqSearchProject.getKeyword())
                    .addValue("SVC_NM", reqSearchProject.getServiceName())
                    .addValue("OPT_ORDR", reqSearchProject.getOptOrder())
                    .addValue("OPT_TYPE", reqSearchProject.getOptType())
                    .addValue("OPT_PAGE_SIZE", reqSearchProject.getOptPageSize())
                    .addValue("OPT_PAGE", reqSearchProject.getOptPage())
                    .addValue("CLOSE_YN", reqSearchProject.getCloseYn());

            Map<String, Object> result = dt.resultWithNames("common", "USP_GET_PROJ_LIST", params, resultNames);
            return resultNames.stream()
                    .collect(Collectors.toMap(
                            name -> name,
                            name -> dt.mapperToDto(getList(result, name), SearchProjectDto.class)
                    ));
        } catch (Exception e) {
            log.error("[SearchProjectRepository/searchProject] ERROR empNo:{},keyword:{},optType:{},optOrder:{},optPageSize:{},optPage:{},closeYn:{},error:{},",
                    reqSearchProject.getEmpNo(), reqSearchProject.getKeyword(), reqSearchProject.getOptType(), reqSearchProject.getOptOrder(),
                    reqSearchProject.getOptPageSize(), reqSearchProject.getOptPage(), reqSearchProject.getCloseYn(), e.getMessage());
            throw e;
        }
    }
}
