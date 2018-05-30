package com.imook.sell.service.impl;

import com.imook.sell.service.BuyerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * to do
 *
 * @author Lucifer
 * @date $(DATE)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BuyerServiceImplTest {

    @Autowired
    private BuyerServiceImpl buyerService;

    @Test
    public void test(){
        buyerService.tset();
    }

}