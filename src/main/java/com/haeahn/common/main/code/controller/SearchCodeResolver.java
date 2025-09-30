package com.haeahn.common.main.code.controller;

import com.haeahn.common.main.code.payload.request.ReqErpCommCode;
import com.haeahn.common.main.code.payload.request.ReqErpHrCode;
import com.haeahn.common.main.code.payload.response.ResErpHrCode;
import com.haeahn.common.main.code.payload.response.ResErpCommCode;
import com.haeahn.common.main.code.service.CodeService;
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
public class SearchCodeResolver {
    private final CodeService codeService;

    @QueryMapping
    public String hello() {
        return "Hello, GraphQL!";
    }

    // *.graphqls - type Qeury 에서 정의한 필드와 일치
    @QueryMapping(name = "queryCodeErpHr")
    public List<ResErpHrCode> queryCodeErpHr(
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
            return result != null ? result : Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @QueryMapping
    public List<ResErpCommCode> queryCodeErpComm(
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
            return result != null ? result : Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
