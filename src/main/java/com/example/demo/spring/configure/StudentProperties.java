package com.example.demo.spring.configure;

import com.example.demo.spring.domain.Student;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Leo liang
 * @Date 2022/2/12
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "config.demo")
public class StudentProperties {

    private Student student1;
    private Student student2;
    private List<Student> students;
    private String driverName;

    private String url;

    private String user;

    private String passWord;

}
