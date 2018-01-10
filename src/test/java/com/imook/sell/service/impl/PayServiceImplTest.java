package com.imook.sell.service.impl;

import com.imook.sell.dto.OrderDto;
import com.imook.sell.service.OrderService;
import com.imook.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * to do
 * @author Lucifer
 * @date $(DATE)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDto orderDto = orderService.findOne("asdfkjshakjdhf");
        payService.create(orderDto);
    }

    @Test
    public void refund(){
        OrderDto orderDto = orderService.findOne("asdfkjshakjdhf");
        payService.refund(orderDto);
    }

}