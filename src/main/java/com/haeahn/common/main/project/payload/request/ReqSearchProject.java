package com.haeahn.common.main.project.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqSearchProject {
    private String empNo;
    private String keyword;
    private String serviceName;
    private String optType;
    private String optOrder;
    private String optPageSize; // items per page
    private String optPage;
    private String closeYn; // "Y" || "N"

    public String getServiceName() {
        return (serviceName == null || serviceName.trim().isBlank()) ? "ALL" : serviceName;
    }

    public String getOptOrder() {
        return (optOrder == null || optOrder.trim().isBlank()) ? "" : optOrder;
    }

    public String getOptPageSize() {
        return (optPageSize == null || optPageSize.trim().isBlank()) ? "9999" : optPageSize;
    }

    public String getOptPage() {
        return (optPage == null || optPage.trim().isBlank()) ? "1" : optPage;
    }

    // 종료 프로젝트 포함여부
    public String getIsClose() {
        return (closeYn == null || closeYn.trim().isBlank()) ? "N" : closeYn;
    }
}
