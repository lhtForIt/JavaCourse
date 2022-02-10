package com.example.demo.spring.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lianght1
 * @date 2022/2/8
 */
@Target({ElementType.TYPE, ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mark {

    boolean marked() default false;

    String value() default "";

    int priority() default -1;


}
