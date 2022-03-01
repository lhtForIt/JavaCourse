package com.example.demo;

import com.example.demo.db.dynamicdatasource.DBService;
import com.example.demo.db.dynamicdatasource.DynamicDataSourceConfig;
import com.example.demo.db.shardingsphere.Course;
import com.example.demo.db.shardingsphere.CourseService;
import com.example.demo.spring.domain.*;
import com.example.demo.spring.jdbc.JDBCUtils;
import com.example.demo.spring.utils.SpringApplicationContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
@MapperScan("com.example.demo.db.*")
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@Import({DynamicDataSourceConfig.class})
public class JvmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JvmApplication.class, args);
//        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Person.class, DemoConfigure.class);
//        System.out.println(annotationConfigApplicationContext.getBeanDefinitionNames());
        ApplicationContext context = SpringApplicationContext.getApplicationContext();
        System.out.println(context.getBean("myPerson"));
        System.out.println(context.getBean("finalPerson"));

        Student student123 = (Student) context.getBean("student123");
        System.out.println(student123.toString());

        student123.print();

        Student student100 = (Student) context.getBean("student100");
        System.out.println(student100.toString());

        student100.print();

        Klass class1 = context.getBean(Klass.class);
        System.out.println(class1);
        System.out.println("Klass对象AOP代理后的实际类型："+class1.getClass());
        System.out.println("Klass对象AOP代理后的实际类型是否是Klass子类："+(class1 instanceof Klass));

        ISchool school = context.getBean(ISchool.class);
        System.out.println(school);
        System.out.println("ISchool接口的对象AOP代理后的实际类型："+school.getClass());

        ISchool school1 = context.getBean(ISchool.class);
        System.out.println(school1);
        System.out.println("ISchool接口的对象AOP代理后的实际类型："+school1.getClass());

        school1.ding();

        class1.dong();

        System.out.println("   context.getBeanDefinitionNames() ===>> "+ String.join(",", context.getBeanDefinitionNames()));

        DBService dbService = (DBService) context.getBean("DBServiceImpl");
        dbService.insertFirstDBUser();
        List<User> user1 = dbService.getFirstDBUser();
        List<User> user2 = dbService.getSecondDBUser();

        CourseService courseService = (CourseService) context.getBean("courseServiceImpl");
        Course c = initData();
        courseService.insertFirstDBUser(c);
        List<Course> course1 = courseService.getFirstCourse();
        List<Course> course2 = courseService.getSecondCourse();

        try {
            JDBCUtils.doOriginalJDBS();
            JDBCUtils.doPrepareStatementJDBC();
            JDBCUtils.doHikariJDBC();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static Course initData() {
        Course c = new Course();
        c.setCid(6L);
        c.setCname("Test");
        c.setUserId(6L);
        c.setCstatus("Y");
        return c;
    }

}
