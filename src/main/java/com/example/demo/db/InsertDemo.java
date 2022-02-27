package com.example.demo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Leo liang
 * @Date 2022/2/27
 */
public class InsertDemo {

    /**
     * 大量数据插入JDBC的PreparedStatement的batch操作是最快的，mybatis的批量会有数据长度限制，
     * 且速度较慢。
     * PreparedStatement批量插入有两个优化，一个是用?rewriteBatchedStatements=true开启批处理，
     * 另一个是conn.setAutoCommit(false);开启手动提交
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        List<OrderEntity> orderList = initData();
        long start = System.currentTimeMillis();
        Connection conn = JDBCUtils.getConnection();

        conn.setAutoCommit(false);
        String sql = "insert into t_order (customer_id,goods_id,coupon_id,actual_payment_amount,payment_amount,payment_method,freight,discount_amount,deduction_amount,order_score,order_status,create_time,creator,modifier) values (" +
                "?,?,?,?," +
                "?,?,?,?," +
                "?,?,?,?," +
                "?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (int i = 0; i < 1000000; i++) {
            OrderEntity entity = orderList.get(i);
            ps.setInt(1, entity.getCustomer_id());
            ps.setInt(2, entity.getGoods_id());
            ps.setInt(3, entity.getCoupon_id());
            ps.setInt(4, entity.getActual_payment_amount());
            ps.setInt(5, entity.getPayment_amount());
            ps.setInt(6, entity.getPayment_method());
            ps.setInt(7, entity.getFreight());
            ps.setInt(8, entity.getDiscount_amount());
            ps.setInt(9, entity.getDeduction_amount());
            ps.setInt(10, entity.getOrder_score());
            ps.setInt(11, entity.getOrder_status());
            ps.setTimestamp(12, entity.getCreate_time());
            ps.setString(13, entity.getCreator());
            ps.setString(14, entity.getModifier());

            ps.addBatch();

            //50000 47686ms
            //5000 61519ms
            //500 53205ms
            /**
             * 这个很奇怪啊，我看别人网上跑的都在10ms之内，我的为啥会要40s?电脑配置原因？
             */
            if ((i+1) % 50000 == 0) {
                ps.executeBatch();
                ps.clearBatch();
            }

        }



        conn.commit();
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start));

        JDBCUtils.closeResource(conn, ps, null);




    }

    private static List<OrderEntity> initData() {

        List<OrderEntity> orderList = new ArrayList<>();

        //为方便这里实体就随便写了
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setActual_payment_amount(10);
        orderEntity.setPayment_amount(100);
        orderEntity.setPayment_method(1);
        orderEntity.setFreight(0);
        orderEntity.setDiscount_amount(90);
        orderEntity.setDeduction_amount(9);
        orderEntity.setOrder_score(1);
        orderEntity.setOrder_status(3);
        orderEntity.setCreate_time(new Timestamp(System.currentTimeMillis()));
        orderEntity.setCreator("Leo");
        orderEntity.setModifier("admin");
        for (int i = 1; i <= 1000000; i++) {
            orderEntity.setCustomer_id(i);
            orderEntity.setCoupon_id(i);
            orderEntity.setGoods_id(i);
            orderList.add(orderEntity);
        }

        return orderList;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
