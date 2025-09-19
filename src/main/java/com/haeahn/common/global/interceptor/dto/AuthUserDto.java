package com.haeahn.common.global.interceptor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter, toString, hashcode 자동 생성
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserDto {
    private String empNo;
    private String roles;
    private boolean authenticated;

//    private String userId;
//    private String userName;
//    private String titleCode;
//    private String titleName;
//    private String deptCode;
//    private String deptName;
//    private String dutyCode;
//    private String dutyName;
}
