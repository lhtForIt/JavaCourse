package com.example.demo.db.dynamicdatasource;

import java.lang.annotation.*;

/**
 * @author Leo liang
 * @Date 2022/2/27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurDataSource {

    String name() default "";

}