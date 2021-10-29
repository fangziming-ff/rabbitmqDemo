package com.fangming.rabbitmqdemo.aop;

/**
 * @author Ming
 * @date 2021/10/15 14:38
 */
import java.lang.annotation.*;

/**
 * 自定义注解可以不包含属性，成为一个标识注解
 * @author DELL
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateAllow {
}
