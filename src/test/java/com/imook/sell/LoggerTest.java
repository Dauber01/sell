package com.imook.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * test logback
 * @author Lucifer
 * @date 2017／12／22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test1(){
        String name = "t1";
        String age = "23";
        log.info("name :{} , age: {}",name,age);
        log.debug("debug...");
        log.error("error...");
    }
}
