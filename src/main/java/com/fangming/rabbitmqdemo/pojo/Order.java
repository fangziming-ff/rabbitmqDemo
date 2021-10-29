package com.fangming.rabbitmqdemo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DELL
 */
@Data
public class Order implements Serializable {

    private String orderId;


    /**
     * 订单状态，订单状态 0：未支付，1：已支付，2：订单已取消
     */
    private Integer orderStatus;


    private String orderName;
}
