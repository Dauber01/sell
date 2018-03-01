package com.imook.sell.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * ①通过＠Async 注解表明该方法是个异步方法，如果注解在类级别，则表明该类所有的
 * 方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor 作为TaskExecutor
 * Author: 王俊超
 * Date: 2017-07-11 07:59
 * All Rights Reserved !!!
 */
@Slf4j
@Service
@Scope("prototype")
public class AsyncTaskService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Async
    public void executeAsyncTask(Integer i) {
        while (true){
            log.info("哈哈啊哈" + i);
            this.redisTemplate.opsForValue().set("test" + i,String.valueOf(i),10, TimeUnit.MILLISECONDS);
        }
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("执行异步任务+1: " + (i + 1));
    }
}
