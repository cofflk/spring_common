package com.haeahn.common.main.codeManager.controller;

import com.haeahn.common.global.dto.ApiResponseDto;
import com.haeahn.common.global.service.ApiResponseService;
import com.haeahn.common.main.codeManager.dto.ErpCommCode;
import com.haeahn.common.main.codeManager.dto.ErpHrCode;
import com.haeahn.common.main.codeManager.payload.request.ReqErpCommCode;
import com.haeahn.common.main.codeManager.payload.request.ReqErpHrCode;
import com.haeahn.common.main.codeManager.payload.response.ResErpCommCode;
import com.haeahn.common.main.codeManager.payload.response.ResErpHrCode;
import com.haeahn.common.main.codeManager.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CodeFinderGraphController {
    private final CodeService codeService;
    private final ApiResponseService apiResponseService;

    @QueryMapping
    public <T> ResponseEntity<ApiResponseDto<List<ResErpHrCode>>> findErpHrCode(
            @Argument String cdPrefix,
            @Argument String cdUpcode,
            @Argument String cdCode,
            @Argument String dsCode
    ) {
        ReqErpHrCode reqErpHrCode = new ReqErpHrCode();
        reqErpHrCode.setCdPrefix(cdPrefix);
        reqErpHrCode.setCdUpcode(cdUpcode);
        reqErpHrCode.setCdCode(cdCode);
        reqErpHrCode.setDsCode(dsCode);
        try {
            List<ResErpHrCode> result =  codeService.getErpHrCode(reqErpHrCode);
            return apiResponseService.success(result, "OK");
        } catch (Exception e) {
            return apiResponseService.failure("FAIL", HttpStatus.NOT_FOUND);
        }
    }

    @QueryMapping
    public <T> ResponseEntity<ApiResponseDto<List<ResErpCommCode>>> findErpCommCode(
            @Argument String cdSystem,
            @Argument String cdType,
            @Argument String cdCode,
            @Argument String dsCode
    ) {
        ReqErpCommCode reqErpCommCode = new ReqErpCommCode();
        reqErpCommCode.setCdSystem(cdSystem);
        reqErpCommCode.setCdType(cdType);
        reqErpCommCode.setCdCode(cdCode);
        reqErpCommCode.setDsCode(dsCode);

        try {
            List<ResErpCommCode> result =  codeService.getErpCommonCode(reqErpCommCode);
            return apiResponseService.success(result, "OK");
        } catch (Exception e) {
            return apiResponseService.failure("FAIL", HttpStatus.NOT_FOUND);
        }
    }



//    @QueryMapping
//    public List<ErpHrCode> findErpHrCode(
//            @Argument String cdPrefix,
//            @Argument String cdUpcode,
//            @Argument String cdCode,
//            @Argument String dsCode
//    ) {
//        ReqErpHrCode reqErpHrCode = new ReqErpHrCode();
//        reqErpHrCode.setCdPrefix(cdPrefix);
//        reqErpHrCode.setCdUpcode(cdUpcode);
//        reqErpHrCode.setCdCode(cdCode);
//        reqErpHrCode.setDsCode(dsCode);
//        return codeRepository.findErpHrCode(reqErpHrCode);
//    }
//
//    @QueryMapping
//    public List<ErpCommCode> findErpCommCode(
//            @Argument String cdSystem,
//            @Argument String cdType,
//            @Argument String cdCode,
//            @Argument String dsCode
//    ) {
//        return codeRepository.findErpCommCode(cdSystem, cdType, cdCode, dsCode);
//    }
}
