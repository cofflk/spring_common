package com.haeahn.common.main.code.controller;

import com.haeahn.common.global.dto.ApiResponseDto;
import com.haeahn.common.global.service.ApiResponseService;
import com.haeahn.common.main.code.payload.request.ReqErpCommCode;
import com.haeahn.common.main.code.payload.request.ReqErpHrCode;
import com.haeahn.common.main.code.payload.response.ResErpHrCode;
import com.haeahn.common.main.code.service.CodeService;
import com.haeahn.common.main.code.payload.response.ResErpCommCode;
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

@Tag(name = "code finder", description = "공통 코드 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/code")
public class SearchCodeController {
    private final CodeService codeService;
    private final ApiResponseService apiResponseService;

    @Operation(summary = "테스트", tags = { "code finder" })
    @PostMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("OK");
    }

    @Operation(summary = "EKP_COMM..USP_GET_ERP_CODE_LIST", description = "ERP DAZT 코드 조회")
    @PostMapping("/erp/hr")
    public <T> ResponseEntity<ApiResponseDto<List<ResErpHrCode>>> searchCodeErpHe(@RequestBody ReqErpHrCode reqErpHrCode) {
        try {
            List<ResErpHrCode> result =  codeService.getErpHrCode(reqErpHrCode);
            return apiResponseService.success(result, "OK");
        } catch (Exception e) {
            return apiResponseService.failure("FAIL", HttpStatus.NOT_FOUND);
        }
    }
//    public ResponseEntity<List<ResErpHrCode>> searchCodeErpHe(@RequestBody ReqErpHrCode reqErpHrCode) {
//        try {
//            List<ResErpHrCode> result =  codeService.getErpHrCode(reqErpHrCode);
//            return ResponseEntity.ok()
////                .header("Custom-Header", "Value")
//                    .body(result);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST);
//        }
//    }

    @Operation(summary = "EKP_COMM..USP_GET_ERP_CODE_LIST", description = "ERP DZZT 코드 조회")
    @PostMapping("/erp/common")
    public <T> ResponseEntity<ApiResponseDto<List<ResErpCommCode>>> searchCodeErpComm(@RequestBody ReqErpCommCode reqErpCommCode) {
        try {
            List<ResErpCommCode> result =  codeService.getErpCommonCode(reqErpCommCode);
            return apiResponseService.success(result, "OK");
        } catch (Exception e) {
            return apiResponseService.failure("FAIL", HttpStatus.NOT_FOUND);
        }
    }
}
