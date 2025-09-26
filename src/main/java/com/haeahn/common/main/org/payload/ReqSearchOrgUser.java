package com.haeahn.common.main.org.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqSearchOrgUser {
    private String empNo;
    private String keyword;
    private String optType;
    private String optPageSize; // items per page
    private String optPage;

    public String getOptPageSize() {
        return (optPageSize == null || optPageSize.trim().isBlank()) ? "9999" : optPageSize;
    }

    public String getOptPage() {
        return (optPage == null || optPage.trim().isBlank()) ? "1" : optPage;
    }
}
