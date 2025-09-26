package com.haeahn.common.main.code.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpCommCode {
    @JsonProperty("CD_SYSTEM")
    private String cdSystem;
    @JsonProperty("CD_TYPE")
    private String cdType;
    @JsonProperty("CD_CODE")
    private String cdCode;
    @JsonProperty("DS_CODE")
    private String dsCode;
    @JsonProperty("DS_SHORT")
    private String dsShort;
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
    @JsonProperty("RM_BIGO")
    private String rmBigo;
    @JsonProperty("DS_ORDER")
    private String dsOrder;
    @JsonProperty("YN_USE")
    private String ynUse;
    // getter / setter
}
