package com.fangming.rabbitmqdemo.async;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;


/**
 * @author Ming
 * @date 2021/10/27 14:17
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class timeTest {

    @Autowired
    private AsyncTask asyncTasks;

    @Test
    public void test() throws Exception {

        long start = System.currentTimeMillis();

        CompletableFuture<String> task1 = asyncTasks.doTaskOne();
        CompletableFuture<String> task2 = asyncTasks.doTaskTwo();
        CompletableFuture<String> task3 = asyncTasks.doTaskThree();

        CompletableFuture.allOf(task1, task2, task3).join();

        long end = System.currentTimeMillis();

        log.info("任务全部完成，总耗时：" + (end - start) + "毫秒");

    }

}
