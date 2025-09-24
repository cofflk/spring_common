package com.haeahn.common.main.codeManager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpHrCode {
    @JsonProperty("CD_PREFIX")
    private String cdPrefix;
    @JsonProperty("CD_CODE")
    private String cdCode;
    @JsonProperty("CD_UPCODE")
    private String cdUpcode;
    @JsonProperty("DS_CODE")
    private String dsCode;
    @JsonProperty("AM_ALLOW")
    private String amAllow;
    @JsonProperty("AM_ALLOW2")
    private String amAllow2;
    @JsonProperty("AM_ALLOW3")
    private String amAllow3;
    @JsonProperty("RM_BIGO")
    private String rmBigo;
    @JsonProperty("YN_DELETE")
    private String ynDelete;
    @JsonProperty("YN_SYS")
    private String ynSys;
    @JsonProperty("SN_PRINT")
    private String snPrint;
    @JsonProperty("DS_ETC1")
    private String dsEtc1;
    @JsonProperty("DS_ETC2")
    private String dsEtc2;
    @JsonProperty("DS_ETC3")
    private String dsEtc3;
    @JsonProperty("DS_ETC4")
    private String dsEtc4;
    @JsonProperty("DS_ETC5")
    private String dsEtc5;
    @JsonProperty("ID_INSERT")
    private String idInsert;
    @JsonProperty("DT_INSERT")
    private String dtInsert;
    @JsonProperty("ID_UPDATE")
    private String idUpdate;
    @JsonProperty("DT_UPDATE")
    private String dtUpdate;
    // getter / setter
}
