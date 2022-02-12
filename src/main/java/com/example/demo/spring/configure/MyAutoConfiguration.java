package com.example.demo.spring.configure;

import com.example.demo.spring.domain.Klass;
import com.example.demo.spring.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leo liang
 * @Date 2022/2/13
 */
@Configuration
@EnableConfigurationProperties(StudentProperties.class)
public class MyAutoConfiguration {

    @Autowired
    private StudentProperties studentProperties;

    @Bean("student123")
    public Student student123(){
//        Student student = new Student();
//        student.setId(123);
//        student.setName("KK123");
        return studentProperties.getStudent1();
    }

    @Bean("student100")
    public Student student100(){
//        Student student = new Student();
//        student.setId(100);
//        student.setName("KK100");

        return studentProperties.getStudent2();
    }

    @Bean("klass")
    public Klass getKlass(){
        Klass klass = new Klass();
        klass.setStudents(studentProperties.getStudents());
        return klass;
    }

}
