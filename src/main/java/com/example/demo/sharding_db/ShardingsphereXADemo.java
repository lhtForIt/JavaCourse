package com.example.demo.sharding_db;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * @author Leo liang
 * @Date 2022/3/7
 *
 * 这块代码完全是参考官方demo照搬的
 *
 * 利用两次插入来认识XA事务，
 * 第一次是connection.commit();
 * 第二次模拟失败是connection.rollback();
 *
 * 我一开始理解的有点偏差，以为需要自己去用XADataSource去拿到对应datasource，其实不用直接用DataSource这个顶级接口去拿即可，
 * connect也是，底层会运用策略模式自动拿到对应的XAconnent.
 *
 * 踩得坑：
 * 1、        <dependency>
 *             <groupId>org.apache.shardingsphere</groupId>
 *             <artifactId>shardingsphere-jdbc-core</artifactId>
 *             <version>5.0.0-alpha</version>
 *         </dependency>
 *         和
 *         <dependency>
 *              <groupId>org.apache.shardingsphere</groupId>
 *              <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
 *              <version>4.1.0</version>
 *          </dependency>
 *  这两个依赖会有冲突，应该是版本不一致的问题，会引发一些莫名其妙的问题，比如读不到配置文件或者什么配置文件不是某个类的子类这种问题，将其中一个注释掉就行。（服了，花了我1小时找原因）
 *  2、   官方demo里面附带的sharding-databases-tables.yaml文件格式有问题，有空格还是什么，会报这个错：Cannot create property=dataSources for JavaBean=org.apache.shardingsphere.infra.yaml.config.YamlRoot
 *  我自己重新拿了个文件在填了一次就好了（花了半小时。。。）
 *
 *
 */
public class ShardingsphereXADemo {

    private static DataSource dataSource;

    public static void main(String[] args) throws IOException, SQLException {

        createDataSource();

        //成功插入
        insert();
        //结果应该为20
        System.out.println(selectAll());

        cleanup();

        createDataSource();
        try {
            insertFailed();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cleanup();
        }


    }

    public static void createDataSource() throws IOException, SQLException {
        File file = new File(ShardingsphereXADemo.class.getResource("/META-INF/sharding-databases-tables.yaml").getFile());
//        File file = new File("F:\\IDEAProject\\Jvm\\src\\main\\resources\\META-INF\\sharding-databases-tables.yaml");
        dataSource = YamlShardingSphereDataSourceFactory.createDataSource(file);
        init();
    }

    public static void init() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS t_order");
            statement.execute("CREATE TABLE t_order (order_id BIGINT AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id))");
            // for PostgreSQL
//            statement.execute("CREATE TABLE IF NOT EXISTS t_order (order_id BIGINT PRIMARY KEY NOT NULL, user_id INT NOT NULL, status VARCHAR(50))");
        }
    }

    public static void cleanup() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS t_order");
        }
    }

    public static void insert() throws SQLException {
        TransactionTypeHolder.set(TransactionType.XA);
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_order (user_id, status) VALUES (?, ?)");
            doInsert(preparedStatement);
            connection.commit();
        } finally {
            TransactionTypeHolder.clear();
        }
    }

    public static void insertFailed() throws SQLException {
        TransactionTypeHolder.set(TransactionType.XA);
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_order (user_id, status) VALUES (?, ?)");
            doInsert(preparedStatement);
            connection.rollback();
        } finally {
            TransactionTypeHolder.clear();
        }
    }

    public static void doInsert(final PreparedStatement preparedStatement) throws SQLException {
        for (int i = 0; i < 10; i++) {
            preparedStatement.setObject(1, i);
            preparedStatement.setObject(2, "init");
            preparedStatement.executeUpdate();
        }
    }

    public static int selectAll() throws SQLException {
        int result = 0;
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT COUNT(1) AS count FROM t_order");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        }
        return result;
    }


}
