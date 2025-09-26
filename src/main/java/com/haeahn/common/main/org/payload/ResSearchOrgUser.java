package com.haeahn.common.main.org.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResSearchOrgUser {
    private String itemSort;
    private String itemType;
    private String itemCode;
    private String itemName;
    private String empNo;
    private String userId;
    private String userName;
    private String companyCode;
    private String companyName;
    private String deptCode;
    private String deptName;
    private String titleCode;
    private String titleName;
    private String dutyCode;
    private String dutyName;
    private String jobCode;
    private String jobName;
    private String deptSort;
    private String dutySort;
    private String jobSort;
    private String titleSort;
    private String userSort;
    private String email;
    private String extension;
    private String mobile;
    private String hasO365;
    private String responsibility;
    private String location;
}
