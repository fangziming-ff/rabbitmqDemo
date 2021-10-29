package com.fangming.rabbitmqdemo.rabbitmq;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Ming
 * @date 2021/10/15 14:29
 */
@Getter
@ToString
public enum ResultCodeEnum {
    SUCCESS(200000,  "成功"),
    SERVER_ERROR(500999,  "失败");

    private Integer code;

    private String message;




    ResultCodeEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
