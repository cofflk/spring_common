package com.haeahn.common.global.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)            // 런타임까지 유지되어야 함
@Documented
public @interface LogAccess {
    String value() default "";
    // 로그 레벨
    String level() default "INFO";
    // 로그 메시지
    String message() default "";

}
