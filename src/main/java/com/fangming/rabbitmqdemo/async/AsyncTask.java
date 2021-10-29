package com.fangming.rabbitmqdemo.async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author Ming
 * @date 2021/10/27 14:16
 */
@Slf4j
@Component
public class AsyncTask {

    public static Random random = new Random();



    @Async
    public CompletableFuture<String> doTaskOne() throws Exception {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("任务一完成");
    }


    @Async
    public CompletableFuture<String> doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("任务二完成");
    }

    @Async
    public CompletableFuture<String>  doTaskThree() throws Exception {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("任务二完成");
    }

}

