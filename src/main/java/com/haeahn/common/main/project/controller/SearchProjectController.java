package com.haeahn.common.main.project.controller;

import com.haeahn.common.global.dto.ApiResponseDto;
import com.haeahn.common.global.service.ApiResponseService;
import com.haeahn.common.main.org.payload.request.ReqSearchOrgUser;
import com.haeahn.common.main.org.payload.response.ResSearchOrgUser;
import com.haeahn.common.main.org.payload.response.ResSearchOrgUserSet;
import com.haeahn.common.main.project.payload.request.ReqSearchProject;
import com.haeahn.common.main.project.payload.response.ResSearchProject;
import com.haeahn.common.main.project.payload.response.ResSearchProjectSet;
import com.haeahn.common.main.project.service.SearchProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Tag(name = "project", description = "프로젝트 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class SearchProjectController {
    private final SearchProjectService searchProjectService;
    private final ApiResponseService apiResponseService;

    @Operation(summary = "EKP_COMM..USP_GET_PROJ_LIST", description = "참여 또는 전체 프로젝트, 북마크 프로젝트")
    @PostMapping("/search")
////    public ResponseEntity<Map<String, String>> getLoginEmpNo(HttpServletRequest request, HttpServletResponse response) {
//    public <T> ResponseEntity<ApiResponseDto<Map<String, List<ResSearchOrgUser>>>> searchOrgUser(@RequestBody ReqSearchOrgUser reqSearchOrg) {
    public <T> ResponseEntity<ApiResponseDto<ResSearchProjectSet>> searchOrgUser(@RequestBody ReqSearchProject reqSearchProject) {
        try {
            Map<String, List<ResSearchProject>> result = searchProjectService.getSearchProject(reqSearchProject);
            ResSearchProjectSet response = ResSearchProjectSet.builder()
                    .book(result.getOrDefault("BOOKMARK", Collections.emptyList()))
                    .result(result.getOrDefault("RESULT", Collections.emptyList()))
                    .build();
            return apiResponseService.success(response, "OK");
        } catch (Exception e) {
            return apiResponseService.failure("FAIL", HttpStatus.NOT_FOUND);
        }
    }
}
