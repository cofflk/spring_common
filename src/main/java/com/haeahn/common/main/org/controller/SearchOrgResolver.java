package com.haeahn.common.main.org.controller;

import com.haeahn.common.main.org.payload.request.ReqSearchOrgUser;
import com.haeahn.common.main.org.payload.response.ResSearchOrgUser;
import com.haeahn.common.main.org.payload.response.ResSearchOrgUserSet;
import com.haeahn.common.main.org.service.SearchOrgService;
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
public class SearchOrgResolver {
    private final SearchOrgService searchOrgService;

    @QueryMapping(name = "querySearchOrgUser")
    public ResSearchOrgUserSet querySearchOrgUser(
            @Argument String empNo,
            @Argument String keyword,
            @Argument String serviceName,
            @Argument String optType,
            @Argument String optPageSize, // items per page
            @Argument String optPage
    ) {
        ReqSearchOrgUser reqSearchOrg = new ReqSearchOrgUser();
        reqSearchOrg.setEmpNo(empNo);
        reqSearchOrg.setKeyword(keyword);
        reqSearchOrg.setServiceName(serviceName);
        reqSearchOrg.setOptType(optType);
        reqSearchOrg.setOptPageSize(optPageSize);
        reqSearchOrg.setOptPage(optPage);

        Map<String, List<ResSearchOrgUser>> result =  searchOrgService.getSearchOrgUser(reqSearchOrg);
        return ResSearchOrgUserSet.builder()
                .book(result.getOrDefault("BOOKMARK", Collections.emptyList()))
                .result(result.getOrDefault("RESULT", Collections.emptyList()))
                .build();
    }

//    @QueryMapping(name = "querySearchOrgUser")
//    public Map<List<ResSearchOrgUser>> querySearchOrgUser(
//            @Argument String empNo,
//            @Argument String keyword,
//            @Argument String optType,
//            @Argument String optPageSize, // items per page
//            @Argument String optPage
//    ) {
//        ReqSearchOrgUser reqSearchOrg = new ReqSearchOrgUser();
//        reqSearchOrg.setEmpNo(empNo);
//        reqSearchOrg.setKeyword(keyword);
//        reqSearchOrg.setOptType(optType);
//        reqSearchOrg.setOptPageSize(optPageSize);
//        reqSearchOrg.setOptPage(optPage);
//
//        try {
//            Map<String, List<ResSearchOrgUser>> result =  searchOrgService.getSearchOrgUser(reqSearchOrg);
//            return result != null ? result : Collections.emptyList();
//        } catch (Exception e) {
//            return Collections.emptyList();
//        }
//
//
//    }
}
