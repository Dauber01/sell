package com.imook.sell.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.management.monitor.Monitor;

/**
 * @author Lucifer
 * @date 2018／03／01 23:54
 */
@Component("mTask")
@Scope("prototype")
@Slf4j
public class MoniotrTask extends Thread {

    @Autowired
    private StringRedisTemplate redisTemplate;

    //参数封装
    private Monitor monitor;

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public String greaterDaoImpl;

    public void test(){
        log.info("1");
    }

    @Override
    public void run() {
        log.info("线程:"+Thread.currentThread().getName()+"运行中.....");
    }

}
