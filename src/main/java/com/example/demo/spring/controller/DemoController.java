package com.example.demo.spring.controller;

import com.example.demo.spring.aspect.Mark;
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

    @RequestMapping("/test")
    @Mark(marked = true, value = "just test info", priority = 2)
    public String test(String name) {
        return "hello," + name;
    }




}
