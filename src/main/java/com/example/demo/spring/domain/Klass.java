package com.example.demo.spring.domain;

import com.example.demo.spring.configure.StudentProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Leo liang
 * @Date 2022/2/12
 */
@Data
//@Component
//@EnableConfigurationProperties(StudentProperties.class)
public class Klass {

    @Autowired
    private StudentProperties studentProperties;

    List<Student> students;

    @PostConstruct
    public void setStudents() {
        this.students = studentProperties.getStudents();
    }

    public void dong(){
        System.out.println(this.getStudents());
    }

}
