package com.fangming.rabbitmqdemo.aop;

import com.fangming.rabbitmqdemo.rabbitmq.Result;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * @author Ming
 * @date 2021/10/15 14:43
 */


@Component
@Scope
@Aspect
public class RateLimitAop {

    private final RateLimiter rateLimiter = RateLimiter.create(2);

    @Pointcut("@annotation(com.fangming.rabbitmqdemo.aop.RateAllow)")
    public void serviceLimit() {

    }

    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Boolean flag = rateLimiter.tryAcquire();
        Object obj = null;
        try {
            if (flag) {
                obj = joinPoint.proceed();
            } else {
                obj = new Result<>(false);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("flag=" + flag + ",obj=" + obj);
        return obj;
    }
}