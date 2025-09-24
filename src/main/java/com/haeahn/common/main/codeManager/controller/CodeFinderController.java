package com.haeahn.common.main.codeManager.controller;

import com.haeahn.common.global.dto.ApiResponseDto;
import com.haeahn.common.global.service.ApiResponseService;
import com.haeahn.common.main.codeManager.payload.request.ReqErpCommCode;
import com.haeahn.common.main.codeManager.payload.request.ReqErpHrCode;
import com.haeahn.common.main.codeManager.payload.response.ResErpCommCode;
import com.haeahn.common.main.codeManager.payload.response.ResErpHrCode;
import com.haeahn.common.main.codeManager.service.CodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Tag(name = "CodeFinder", description = "공통 코드 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/code")
public class CodeFinderController {
    private final CodeService codeService;
    private final ApiResponseService apiResponseService;

    @Operation(summary = "ERP HR 코드 조회", tags = { "code finder" })
    @PostMapping("/erp/hr")
    public <T> ResponseEntity<ApiResponseDto<List<ResErpHrCode>>> hrCodeFinder(@RequestBody ReqErpHrCode reqErpHrCode) {
        try {
            List<ResErpHrCode> result =  codeService.getErpHrCode(reqErpHrCode);
            return apiResponseService.success(result, "OK");
        } catch (Exception e) {
            return apiResponseService.failure("FAIL", HttpStatus.NOT_FOUND);
        }
    }
//    public ResponseEntity<List<ResErpHrCode>> hrCodeFinder(@RequestBody ReqErpHrCode reqErpHrCode) {
//        try {
//            List<ResErpHrCode> result =  codeService.getErpHrCode(reqErpHrCode);
//            return ResponseEntity.ok()
////                .header("Custom-Header", "Value")
//                    .body(result);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST);
//        }
//    }

    @Operation(summary = "토큰 재발급 ACCESS", tags = { "api" })
    @PostMapping("/erp/common")
    public <T> ResponseEntity<ApiResponseDto<List<ResErpCommCode>>> commCodeFinder(@RequestBody ReqErpCommCode reqErpCommCode) {
        try {
            List<ResErpCommCode> result =  codeService.getErpCommonCode(reqErpCommCode);
            return apiResponseService.success(result, "OK");
        } catch (Exception e) {
            return apiResponseService.failure("FAIL", HttpStatus.NOT_FOUND);
        }
    }
}
