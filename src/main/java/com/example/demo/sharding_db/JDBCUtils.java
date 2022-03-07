package com.example.demo.sharding_db;

import com.example.demo.spring.jdbc.JDBCConfigure;

import java.sql.*;

/**
 * @author Leo liang
 * @Date 2022/3/7
 */
public class JDBCUtils {

    private static String sqlStatement1;

    /**
     * 测试入口
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        //通过连接创建数据库执行对象
        PreparedStatement ps = null;
        //为查询的结果集准备接收对象
        ResultSet rs = null;
        //查询
        sqlStatement1 = "SELECT * FROM t_order WHERE user_id in (?,?)";
        ps = con.prepareStatement(sqlStatement1);
        ps.setObject(1, 1);
        ps.setObject(2, 2);
        qry1(ps,rs);
        //增加
        sqlStatement1 = "INSERT INTO t_order (user_id,status) VALUES(?,?)";
        ps = con.prepareStatement(sqlStatement1);
        ps.setObject(1, 3);
        ps.setObject(2, "OK");
        System.out.println("插入执行结果:"+update1(ps));
        //更新
        sqlStatement1 = "UPDATE t_order SET status=? WHERE user_id = ?";
        ps = con.prepareStatement(sqlStatement1);
        ps.setObject(1, "Fail");
        ps.setObject(2, 3);
        System.out.println("更新执行结果:"+update1(ps));
        //删除
        sqlStatement1 = "DELETE FROM t_order WHERE user_id = ?";
        ps = con.prepareStatement(sqlStatement1);
        ps.setObject(1, 3);
        System.out.println("删除执行结果:"+update1(ps));
        JDBCConfigure.closeResource(con, ps, rs);
    }

    public static Connection getConnection() throws ClassNotFoundException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/sharding_db", "root", "root");
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



    private static void qry1(PreparedStatement sta,ResultSet rs) throws SQLException {
        rs = sta.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getObject("user_id"));
        }
    }

    private static int update1(PreparedStatement sta) throws SQLException {
        return sta.executeUpdate();
    }


}
