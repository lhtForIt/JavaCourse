package com.example.demo.spring.controller;

import com.example.demo.spring.aspect.Mark;
import com.example.demo.spring.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lianght1
 * @date 2022/2/8
 */
@RequestMapping("/demo")
@RestController
public class DemoController {

    @Autowired
    @Qualifier("finalPerson")
    private Person p1;

    @RequestMapping("/test")
    @Mark(marked = true, value = "just test info", priority = 2)
    public String test(String name) {
        return "hello," + p1.getName();
    }




}
