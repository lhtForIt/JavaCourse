package com.example.demo.spring.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Leo liang
 * @Date 2022/2/12
 */
@Data
@Component("myPerson")
@Scope("prototype")
@ToString
public class Person {

    private String name;
    private String sex;
    private String desc;
    private String hobby;
    private int age;
    private int high;
    private int weight;


}
