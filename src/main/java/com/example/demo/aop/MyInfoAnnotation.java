package com.example.demo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program:
 * @description: 用以指定切点的注解
 * @author: malili
 * @create: 2018-04-25 17:33
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.PARAMETER})
public @interface MyInfoAnnotation {
    String value() default "hao";
}
