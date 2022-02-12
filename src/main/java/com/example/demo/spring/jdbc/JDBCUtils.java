package com.example.demo.spring.jdbc;

import com.example.demo.spring.domain.User;
import com.example.demo.spring.jdbc.mapper.myMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.stereotype.Component;

import java.beans.Transient;
import java.sql.*;

/**
 * @author Leo liang
 * @Date 2022/2/13
 */
@Component
public class JDBCUtils {

    private static String sqlStatement;
    private static String sqlStatement1;
    @Autowired
    private static myMapper mapper;

    public static void doOriginalJDBS() throws SQLException {
        Connection con = JDBCConfigure.getConnection();
        //通过连接创建数据库执行对象
        Statement sta = con.createStatement();
        //为查询的结果集准备接收对象
        ResultSet rs = null;
        //查询
        sqlStatement = "SELECT * FROM user";
        qry(sta,sqlStatement,rs);
        //增加
        sqlStatement = "INSERT INTO user ('name','sex','age') VALUES('leo','男','18')";
        System.out.println("插入执行结果:"+update(sta,sqlStatement));
        //更新
        sqlStatement = "UPDATE user SET name='mark' WHERE age = '18'";
        System.out.println("更新执行结果:"+update(sta,sqlStatement));
        //删除
        sqlStatement = "DELETE FROM user WHERE age = '18'";
        System.out.println("删除执行结果:"+update(sta,sqlStatement));
        JDBCConfigure.closeResource(con, sta, rs);
    }

    public static void doPrepareStatementJDBC() throws SQLException {
        Connection con = JDBCConfigure.getConnection();
        //通过连接创建数据库执行对象
        PreparedStatement ps = null;
        //为查询的结果集准备接收对象
        ResultSet rs = null;
        //查询
        sqlStatement1 = "SELECT * FROM user WHERE age = ?";
        ps = con.prepareStatement(sqlStatement1);
        ps.setObject(1, "18");
        qry1(ps,rs);
        //增加
        sqlStatement1 = "INSERT INTO user ('name','sex','age') VALUES(?,?,?)";
        ps = con.prepareStatement(sqlStatement1);
        ps.setObject(1, "leo");
        ps.setObject(2, "男");
        ps.setObject(3, "18");
        System.out.println("插入执行结果:"+update1(ps));
        //更新
        sqlStatement1 = "UPDATE user SET name=? WHERE age = ?";
        ps = con.prepareStatement(sqlStatement1);
        ps.setObject(1, "mark");
        ps.setObject(2, "18");
        System.out.println("更新执行结果:"+update1(ps));
        //删除
        sqlStatement1 = "DELETE FROM user WHERE age = ?";
        ps = con.prepareStatement(sqlStatement1);
        ps.setObject(1, "18");
        System.out.println("删除执行结果:"+update1(ps));
        JDBCConfigure.closeResource(con, ps, rs);

    }

    public static void doHikariJDBC(){
        System.out.println("查询：" + mapper.getUser(18));
        User user = new User();
        user.setName("leo");
        user.setSex("男");
        user.setAge(18);
        mapper.addUser(user);
        User user1 = new User();
        user1.setName("mark");
        mapper.updateUser(user1, user);
        mapper.deleteUser(user);
    }

    public static void qry(Statement sta, String sql, ResultSet rs) throws SQLException {
        rs = sta.executeQuery(sql);
        while(rs.next()) {
            System.out.println(rs.getObject("name"));
        }
    }

    public static int update(Statement sta,String sql) throws SQLException {
        return sta.executeUpdate(sql);
    }

    private static void qry1(PreparedStatement sta,ResultSet rs) throws SQLException {
        rs = sta.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getObject("deptno"));
        }
    }

    private static int update1(PreparedStatement sta) throws SQLException {
        return sta.executeUpdate();
    }


}
