package com.haeahn.common.main.org.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchOrgUserDto {
    @JsonProperty("ITEM_SORT")
    private String itemSort;
    @JsonProperty("ITEM_TY")
    private String itemType;
    @JsonProperty("ITEM_CD")
    private String itemCode;
    @JsonProperty("ITEM_NM")
    private String itemName;
    @JsonProperty("EMP_NO")
    private String empNo;
    @JsonProperty("USER_ID")
    private String userId;
    @JsonProperty("USER_NM")
    private String userName;
    @JsonProperty("COM_CD")
    private String companyCode;
    @JsonProperty("COM_NM")
    private String companyName;
    @JsonProperty("DEPT_CD")
    private String deptCode;
    @JsonProperty("DEPT_NM")
    private String deptName;
    @JsonProperty("TITLE_CD")
    private String titleCode;
    @JsonProperty("TITLE_NM")
    private String titleName;
    @JsonProperty("DUTY_CD")
    private String dutyCode;
    @JsonProperty("DUTY_NM")
    private String dutyName;
    @JsonProperty("JOB_CD")
    private String jobCode;
    @JsonProperty("JOB_NM")
    private String jobName;
    @JsonProperty("DEPT_SORT")
    private String deptSort;
    @JsonProperty("DUTY_SORT")
    private String dutySort;
    @JsonProperty("JOB_SORT")
    private String jobSort;
    @JsonProperty("TITLE_SORT")
    private String titleSort;
    @JsonProperty("USER_SORT")
    private String userSort;
    @JsonProperty("EMAIL")
    private String email;
    @JsonProperty("COMP_TEL")
    private String extension;
    @JsonProperty("MOBILE")
    private String mobile;
    @JsonProperty("O365_YN")
    private String hasO365;
    @JsonProperty("LEVEL_DETAIL")
    private String responsibility;
    @JsonProperty("SEAT_TXT")
    private String location;
    @JsonProperty("BOOKMARK_YN")
    private String isBookmark;
}
