package com.haeahn.common.main.project.controller;

import com.haeahn.common.main.project.payload.request.ReqSearchProject;
import com.haeahn.common.main.project.payload.response.ResSearchProject;
import com.haeahn.common.main.project.payload.response.ResSearchProjectSet;
import com.haeahn.common.main.project.service.SearchProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SearchProjectResolver {
    private final SearchProjectService searchProjectService;

    @QueryMapping(name = "querySearchProject")
    public ResSearchProjectSet querySearchProject(
            @Argument String empNo,
            @Argument String keyword,
            @Argument String serviceName,
            @Argument String optType,
            @Argument String optOrder,
            @Argument String optPageSize, // items per page
            @Argument String optPage,
            @Argument String closeYn
    ) {
        ReqSearchProject reqSearchProject = new ReqSearchProject();
        reqSearchProject.setEmpNo(empNo);
        reqSearchProject.setKeyword(keyword);
        reqSearchProject.setServiceName(serviceName);
        reqSearchProject.setOptType(optType);
        reqSearchProject.setOptOrder(optOrder);
        reqSearchProject.setOptPageSize(optPageSize);
        reqSearchProject.setOptPage(optPage);
        reqSearchProject.setCloseYn(closeYn);

        Map<String, List<ResSearchProject>> result =  searchProjectService.getSearchProject(reqSearchProject);
        return ResSearchProjectSet.builder()
                .book(result.getOrDefault("BOOKMARK", Collections.emptyList()))
                .result(result.getOrDefault("RESULT", Collections.emptyList()))
                .build();
    }
}
