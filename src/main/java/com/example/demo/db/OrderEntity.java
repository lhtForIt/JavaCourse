package com.example.demo.db;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Leo liang
 * @Date 2022/2/27
 */
@Data
public class OrderEntity {
    /**
     * mysql里面order是关键字不能用
     * CREATE TABLE t_order (
     *   t_order_id INT AUTO_INCREMENT COMMENT '订单ID',
     *   customer_id INT not null comment '下单用户ID',
     *   goods_id int not null comment '商品表信息主键ID',
     *   coupon_id int not null comment '收件人信息表主键ID',
     *   actual_payment_amount int not null comment '实际支付信息-减去所有优惠和折扣之后实际支付金额',
     *   payment_amount int not null comment '应付金额-本来应该支付多少钱',
     *   payment_method int not null comment '付款方式：1 支付宝，2 微信 3，银联',
     *   freight int comment '运费',
     *   discount_amount int comment '优惠金额',
     *   deduction_amount int comment '本订单的抵扣金额-订单积分换取或者某些礼品卡抵扣等',
     *   order_score int comment '订单产生积分，可以抵扣现金',
     *   order_status int default 1 comment '订单状态 1 支付中 2 取消 3 已支付 4 请求退款',
     *   create_time TIMESTAMP NOT NULL COMMENT '创建时间',
     *   creator varchar(100) not null comment '创建人',
     *   modify_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     *   modifier varchar(100) not null comment '修改人',
     *   PRIMARY KEY pk_order(t_order_id)
     * ) ENGINE = innodb COMMENT '订单表';
     */

    private int t_order_id;
    private int customer_id;
    private int goods_id;
    private int coupon_id;
    private int actual_payment_amount;
    private int payment_amount;
    private int payment_method;
    private int freight;
    private int discount_amount;
    private int deduction_amount;
    private int order_score;
    private int order_status;
    private Timestamp create_time;
    private Timestamp modify_time;
    private String creator;
    private String modifier;


}
