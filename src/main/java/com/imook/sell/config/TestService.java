package com.imook.sell.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 多线程测试类
 * @author Lucifer
 * @date 2018／03／01 23:11
 */
@Service
@Slf4j
public class TestService {

    @Autowired
    AsyncTaskService asyncTaskService;

    public void commonTest(){
        /*MoniotrTask m1=   ApplicationContextProvider.getBean("mTask", MoniotrTask.class);
        MoniotrTask m2=ApplicationContextProvider.getBean("mTask", MoniotrTask.class);
        MoniotrTask m3=ApplicationContextProvider.getBean("mTask", MoniotrTask.class);*/
        /*AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskExecutorConfig.class);

        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);*/
        int i = 0;
        while (i < 10){
            asyncTaskService.executeAsyncTask(i);
            i++;
        }
        /*m1.run();
        m2.run();
        m3.run();*/
        /*System.out.println(m1+" => "+m1.greaterDaoImpl);
        System.out.println(m2+" => "+m2.greaterDaoImpl);
        System.out.println(m3+" => "+m3.greaterDaoImpl);*/
    }

}
