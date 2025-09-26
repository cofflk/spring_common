package com.haeahn.common.main.org.controller;

import com.haeahn.common.main.org.payload.ReqSearchOrgUser;
import com.haeahn.common.main.org.payload.ResSearchOrgUser;
import com.haeahn.common.main.org.service.SearchOrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SearchOrgResolver {
    private final SearchOrgService searchOrgService;

    @QueryMapping(name = "querySearchOrgUser")
    public List<ResSearchOrgUser> querySearchOrgUser(
            @Argument String empNo,
            @Argument String keyword,
            @Argument String optType,
            @Argument String optPageSize, // items per page
            @Argument String optPage
    ) {
        ReqSearchOrgUser reqSearchOrg = new ReqSearchOrgUser();
        reqSearchOrg.setEmpNo(empNo);
        reqSearchOrg.setKeyword(keyword);
        reqSearchOrg.setOptType(optType);
        reqSearchOrg.setOptPageSize(optPageSize);
        reqSearchOrg.setOptPage(optPage);

        try {
            List<ResSearchOrgUser> result =  searchOrgService.getSearchOrgUser(reqSearchOrg);
            return result != null ? result : Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }


    }
}
