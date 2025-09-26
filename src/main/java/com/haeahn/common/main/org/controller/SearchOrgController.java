package com.haeahn.common.main.org.controller;

import com.haeahn.common.global.dto.ApiResponseDto;
import com.haeahn.common.global.service.ApiResponseService;
import com.haeahn.common.main.org.payload.ReqSearchOrgUser;
import com.haeahn.common.main.org.payload.ResSearchOrgUser;
import com.haeahn.common.main.org.service.SearchOrgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "org", description = "임직원 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/org")
public class SearchOrgController {
    private final SearchOrgService searchOrgService;
    private final ApiResponseService apiResponseService;

    @Operation(summary = "EKPORG..USP_GET_USER_LIST", description = "부서, 직책, 직무, 직위, 개인")
    @PostMapping("/search")
//    public ResponseEntity<Map<String, String>> getLoginEmpNo(HttpServletRequest request, HttpServletResponse response) {
    public <T> ResponseEntity<ApiResponseDto<List<ResSearchOrgUser>>> searchOrgUser(@RequestBody ReqSearchOrgUser reqSearchOrg) {
        try {
            List<ResSearchOrgUser> result = searchOrgService.getSearchOrgUser(reqSearchOrg);
            return apiResponseService.success(result, "OK");
        } catch (Exception e) {
            return apiResponseService.failure("FAIL", HttpStatus.NOT_FOUND);
        }
    }
}
