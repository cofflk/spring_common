package com.haeahn.common.global.aop.logging;

import com.haeahn.common.global.aop.utils.AopCommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "logging.file")
@Aspect
@Slf4j
public class LogTestAop {
//  logback - 로그 처리 프레임워크
//  slf4j - 로그 프레임워크 추상화한 인터페이스
//  log level  FETAL > ERROR > WARN > INFO > DEBUG > TRACE

    @Autowired
    private AopCommonUtils aopCommon;


//    logging:
//      file:
//          name: dev-${spring.application.name}
//          path: D:\CodeH\03_backEnd\log\${spring.application.name}
    private Map<String, LogFile>  logFile;
    public static class LogFile {
        private String name;
        private String path;
    }

//    private Logger logger = LoggerFactory.getLogger(SaveLogAop.class);

//  @Around("${pattern}"} : 해당 패턴 실행 전, 후 모두 동작
//  ProceedingJoinPoint > @Around 전용
//    메소드 실행 여부 제어 가능
    @Around("execution(* com.haeahn.svr_work.workflow..*(..))")
//    @Around("execution(* com.haeahn.svr_work.workflow..controller..*(..))")
    public Object AroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메소드 정보
        Method method = aopCommon.getMethod(joinPoint);
//        log.info("=== AroundLog/method name : ", method.getName());
        try {
//            log.info("joinPoint.getSignature().getName() : ", joinPoint.getSignature().getName());
            return joinPoint.proceed();
        }
        finally {
            log.info("AfterReturnLog end");
        }
    }




//  @Before("${pattern}") : 해당 패턴 메소드 실행 전 동작
//    @Before("execution(* com.haeahn.svr_work.workflow..*(..))")

//  @After("${pattern}") : 해당 패턴 메소드 실행 후 동작
//    @After("execution(* com.haeahn.svr_work.workflow..*(..))")

//    pointcut 표현 - * = 모든것, .. = 0개 이상
//    within : 특정 클래스 안에 모든 메소드 지정 > e.g.) @Around("within(com.pamyferret.controller.*)")
//    @within : 클래스를 지정 > e.g.) @within(org.springframework.stereotype.Controller)

    // 공통적으로 실행할 부분을 pointcut 으로 지정, cut() 으로 사용
//    @Pointcut("execution(* com.haeahn.svr_work..*.*(..))")
    @Pointcut("execution(* com.haeahn.svr_work.workflow.*(..))")
    private void cut() {}

//    @After("execution(* com.haeahn.svr_work.workflow..*(..))")
//    @After("cut()")
//    public Object AfterLog(ProceedingJoinPoint joinPoint) throws Throwable {

    @AfterReturning(value = "cut()", returning = "result")
    public void AfterReturnLog(JoinPoint joinPoint, Object result) throws Throwable {

        // 메소드 정보
        Method method = aopCommon.getMethod(joinPoint);
        log.info("=== AfterReturnLog/method name : ", method.getName());
        log.info("=== AfterReturnLog/method type  : ", result.getClass().getSimpleName());
        log.info("=== AfterReturnLog/method value   : ", result.getClass().getSimpleName());
    }

}
