package com.haeahn.common.main.code.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResErpCommCode {
    private String cdSystem;
    private String cdType;
    private String cdCode;
    private String dsCode;
    private String dsShort;
    private String dsEtc1;
    private String dsEtc2;
    private String dsEtc3;
    private String dsEtc4;
    private String dsEtc5;
    private String rmBigo;
    private String dsOrder;
}
