package com.imook.sell.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * ①利用＠EnableAsync 注解开启异步任务支持。
 * ②配置类实现AsyncConfigurer 接口并重写getAsyncExecutor 方法，并返回一个
 * ThreadPoolTaskExecutor ，这样我们就获得了一个基于线程池TaskExecutor。
 * Author: 王俊超
 * Date: 2017-07-11 07:57
 * All Rights Reserved !!!*/




@Configuration
//@ComponentScan("com.imook.sell.config")
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer { // 1
    @Override
    public Executor getAsyncExecutor() { // 2
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(20);
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
