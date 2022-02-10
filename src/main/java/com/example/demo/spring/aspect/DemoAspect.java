package com.example.demo.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lianght1
 * @date 2022/2/8
 */
@Aspect
@Configuration
public class DemoAspect {

//    @Pointcut("execution(* com.example.demo.spring.controller.*.*(..))")
//    public void point(){}

    @Pointcut("@annotation(com.example.demo.spring.aspect.Mark)")
    public void point1(){}

    @Before("point1()")
    public void before1(JoinPoint joinPoint){
        System.out.println("--------执行before1()---------");
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Mark mark = ms.getMethod().getAnnotation(Mark.class);
        System.out.println(mark == null ? "" : "--------拿到了注解Mark:------------");
        System.out.println("------------Marked值为：" + mark.marked() + "----------------------");
        System.out.println("------------value值为：" + mark.value() + "----------------------");
        System.out.println("------------priority值为：" + mark.priority() + "----------------------");
    }

//    @Before("point()")
//    public void before(){
//        System.out.println("--------执行before()---------");
//    }
//
//    @After("point()")
//    public void after(){
//        System.out.println("--------执行after()---------");
//    }
//
//    @Around("point()")
//    public void around(ProceedingJoinPoint joinpoint) throws Throwable {
//        System.out.println("--------执行around()---------");
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String ipAddr = request.getHeader("x-forwarded-for");
//        String url = request.getRequestURL().toString();
//        Object result = joinpoint.proceed();
//        System.out.println("----------ipAddr:" + ipAddr + "-----------");
//        System.out.println("----------url:" + url + "-----------");
//        System.out.println("----------result:" + result + "-----------");
//        System.out.println("----------method:" + request.getMethod() + "-----------");
//        System.out.println("--------结束around()---------");
//    }


}
