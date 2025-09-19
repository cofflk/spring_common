package com.haeahn.common.global.aop.auth;

import com.haeahn.common.global.aop.utils.AopCommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;





// Authorization 인가
@Component
@Aspect
//@ConfigurationProperties(prefix = "logging.file")
//@Slf4j // > log.info 사용
public class AuthorizationAop {

    @Autowired
    private AopCommonUtils common;

    // logback-spring.xml 에서 설정한 logger
    private static final Logger logger = LoggerFactory.getLogger("AuthorizationAop");

    // 메뉴 권한 체크
    @Around("@annotation(com.haeahn.svr_work.global.AOP.Annotation.IAccessMenuAop)")
    public Object AuthAccessMenu(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메소드 정보
//        Method method = common.getMethod(joinPoint);
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("controller start - access menu : " + methodName + " / " + Arrays.toString(args));
        logger.info("================== Check Access Menu ");
        logger.info("Method name: " + methodName + ";");
        logger.info("Method args : " + Arrays.toString(args) + ";");

        try {
            logger.info("success ===");
            return joinPoint.proceed();
        }
        catch(Exception e) {
            logger.warn("fail : " + e.toString());
            throw e;
        }
        finally {
            logger.info("finally ===");
            System.out.println("controller end - access menu : " + methodName);
        }
    }

//    @Before("execution(* com.example.controller.*.*(..))")
//    public void beforeControllerMethod(JoinPoint joinPoint) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            Arrays.stream(cookies).forEach(cookie -> {
//                if ("cookieName".equals(cookie.getName())) {
//                    String cookieValue = cookie.getValue();
//                    // Perform actions with the cookie value
//                    System.out.println("Cookie Value: " + cookieValue);
//                }
//            });
//        }
//    }


    
    // controller 접근 체크
//    @Around("execution(* com.haeahn.svr_work.workflow.(..).controller.*(..))")
    @Around("execution(* com.haeahn.svr_work.workflow..controller..*(..))")
    public Object ArondAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메소드 정보
        Method method = common.getMethod(joinPoint);

        System.out.println("controller start : " + method.getName());
        try {
            return joinPoint.proceed();
        }
        finally {
            System.out.println("controller end : " + method.getName());
        }
    }


}
