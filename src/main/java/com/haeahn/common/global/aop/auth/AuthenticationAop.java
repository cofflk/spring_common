package com.haeahn.common.global.aop.auth;

import com.haeahn.common.global.aop.utils.AopCommonUtils;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 로그인 인증 체크
@Component
@Aspect
public class AuthenticationAop {

    @Autowired
    private AopCommonUtils common;


}
