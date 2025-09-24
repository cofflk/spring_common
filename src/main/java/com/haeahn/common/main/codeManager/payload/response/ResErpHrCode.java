package com.haeahn.common.main.codeManager.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResErpHrCode {
    private String cdPrefix;
    private String cdCode;
    private String cdUpcode;
    private String dsCode;
    private String amAllow;
    private String amAllow2;
    private String amAllow3;
    private String rmBigo;
    private String snPrint;
    private String dsEtc1;
    private String dsEtc2;
    private String dsEtc3;
    private String dsEtc4;
    private String dsEtc5;
}
