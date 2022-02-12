package com.example.demo.spring.jdbc;

import com.example.demo.spring.configure.StudentProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;

/**
 * @author Leo liang
 * @Date 2022/2/13
 */
@Data
@Component
@EnableConfigurationProperties(StudentProperties.class)
public class JDBCConfigure {

    @Autowired
    private StudentProperties studentProperties;
    private static StudentProperties myStudentProperties;

    @PostConstruct
    public void setMyStudentProperties() {
        myStudentProperties = studentProperties;
        //初始化驱动，一般是用静态代码块，但是会在postcinstruct之前，所以放在这里做
        try {
            Class.forName(myStudentProperties.getDriverName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(myStudentProperties.getUrl(), myStudentProperties.getUser(), myStudentProperties.getPassWord());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接异常，请检查配置数据");
        }
        return con;
    }

    public static void closeResource(Connection con, Statement sta, ResultSet rs) {
        try {
            if(con!=null) {
                con.close();
            }
            if(sta!=null) {
                sta.close();
            }
            if(rs!=null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
