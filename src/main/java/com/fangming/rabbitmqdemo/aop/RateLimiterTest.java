package com.fangming.rabbitmqdemo.aop;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ming
 * @date 2021/10/15 12:06
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        /**这里的1表示每秒允许处理的量为1个*/
        RateLimiter limiter = RateLimiter.create(1.0);
        for (int i = 1; i <= 10; i++) {
            limiter.acquire();// 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);
        }
        String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("start time:" + start);
        System.out.println("end time:" + end);
    }
}
