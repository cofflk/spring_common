package com.haeahn.common.main.codeManager.service;

import com.haeahn.common.main.codeManager.dto.ErpCommCode;
import com.haeahn.common.main.codeManager.dto.ErpHrCode;
import com.haeahn.common.main.codeManager.payload.request.ReqErpCommCode;
import com.haeahn.common.main.codeManager.payload.request.ReqErpHrCode;
import com.haeahn.common.main.codeManager.payload.response.ResErpCommCode;
import com.haeahn.common.main.codeManager.payload.response.ResErpHrCode;
import com.haeahn.common.main.codeManager.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeService {
    private final CodeRepository codeRepository;

    public List<ResErpHrCode> getErpHrCode(ReqErpHrCode reqErpHrCode) {
        List<ErpHrCode> result = codeRepository.findErpHrCode(reqErpHrCode);

        return result.stream()
                .map(item -> ResErpHrCode.builder()
                        .cdPrefix(item.getCdPrefix())
                        .cdUpcode(item.getCdUpcode())
                        .cdCode(item.getCdCode())
                        .dsCode(item.getDsCode())
                        .amAllow(item.getAmAllow())
                        .amAllow2(item.getAmAllow2())
                        .amAllow3(item.getAmAllow3())
                        .rmBigo(item.getRmBigo())
                        .snPrint(item.getSnPrint())
                        .dsEtc1(item.getDsEtc1())
                        .dsEtc2(item.getDsEtc2())
                        .dsEtc3(item.getDsEtc3())
                        .dsEtc4(item.getDsEtc4())
                        .dsEtc5(item.getDsEtc5())
                        .build()
                )
                .toList();
    }

    public List<ResErpCommCode> getErpCommonCode(ReqErpCommCode reqErpCommCode) {
        List<ErpCommCode> result = codeRepository.findErpCommCode(reqErpCommCode);

        return result.stream()
                .map(item -> ResErpCommCode.builder()
                        .cdSystem(item.getCdSystem())
                        .cdType(item.getCdType())
                        .cdCode(item.getCdCode())
                        .dsCode(item.getDsCode())
                        .dsShort(item.getDsShort())
                        .rmBigo(item.getRmBigo())
                        .dsOrder(item.getDsOrder())
                        .dsEtc1(item.getDsEtc1())
                        .dsEtc2(item.getDsEtc2())
                        .dsEtc3(item.getDsEtc3())
                        .dsEtc4(item.getDsEtc4())
                        .dsEtc5(item.getDsEtc5())
                        .build()
                )
                .toList();
    }

}

