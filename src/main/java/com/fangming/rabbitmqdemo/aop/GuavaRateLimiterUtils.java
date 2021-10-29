package com.fangming.rabbitmqdemo.aop;


import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * @author Ming
 * @date 2021/10/15 13:24
 */
@Service
public class GuavaRateLimiterUtils {
    RateLimiter rateLimiter = RateLimiter.create(5);

    public Boolean tryAcquire(){
       return rateLimiter.tryAcquire();
    }
}
