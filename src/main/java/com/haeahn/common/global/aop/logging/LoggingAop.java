package com.haeahn.common.global.aop.logging;

import com.haeahn.common.global.annotation.LogAccess;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggingAop {
    //    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static final Logger accessLog = LoggerFactory.getLogger("AccessLog");


    @Pointcut("@annotation(LogAccess)")
    public void logAccessPointcut() {}

    //    @Around("execution(* com.haeahn.svr_work.workflow..*(..))")
//    @Around("@annotation(LogAccess)")
    @Around("logAccessPointcut()")  // 메서드에 붙은 어노테이션을 감지
    public Object AccessLog(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        LogAccess log = method.getAnnotation(LogAccess.class);  // 리플렉션으로 가져와야 함
        String value = log.value();
        String level = log.level();
        String message = log.message();

        String methodName = method.getName();
        String className = method.getDeclaringClass().getSimpleName();
//        String logCaller = logAccess.value().isEmpty() ? String.format("[%s/%s]", className, methodName) : logAccess.value();
        String logMessage = String.format("AOP 로그 [%s] class:%s,method:%s,message:%s,",
                log.value(),className, methodName,log.message());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            switch (level.toUpperCase()) {
                case "DEBUG" -> accessLog.debug(logMessage);
                case "WARN" -> accessLog.warn(logMessage);
                case "ERROR" -> accessLog.error(logMessage);
                default -> accessLog.info(logMessage);
            }
            return joinPoint.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            stopWatch.stop();
        }
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}
