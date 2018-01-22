package com.imook.sell.service.impl;

import com.imook.sell.dto.OrderDto;
import com.imook.sell.service.OrderService;
import com.imook.sell.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PushMessageService pushMessageService;

    @Test
    public void orderStatus() throws Exception {
        OrderDto orderDto = orderService.findOne("123456");
        pushMessageService.orderStatus(orderDto);
    }

}