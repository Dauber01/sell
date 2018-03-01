package com.imook.sell.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    public void commonTest() throws Exception {
        long start = System.currentTimeMillis();
        testService.commonTest();
        long end = System.currentTimeMillis();
        System.out.println("运行时间为" + (end - start) + "ms");
    }

}