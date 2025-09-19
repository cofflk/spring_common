package com.haeahn.common.global.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 메소드에 적용
@Target(ElementType.METHOD)
// 런타임에 유지되어야 AOP에서 인식
@Retention(RetentionPolicy.RUNTIME)
public @interface ILoginAop {
}
