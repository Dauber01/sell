package com.imook.sell.service.impl;

import com.imook.sell.dataobject.OrderDetail;
import com.imook.sell.dto.OrderDto;
import com.imook.sell.enums.OrderStatusEnum;
import com.imook.sell.enums.PayStatusEnum;
import com.imook.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String buyerOpenId = "110110";

    private final String orderId = "1514707974135310351";

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerOpenid(buyerOpenId);
        orderDto.setBuyerAddress("西南");
        orderDto.setBuyerName("张飞");
        orderDto.setBuyerPhone("15004011200");

        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(1);
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123457");
        orderDetail1.setProductQuantity(2);
        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail1);

        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result = orderService.create(orderDto);
        log.info("【创建订单】result = {}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDto orderDto = orderService.findOne(orderId);
        Assert.assertEquals(orderId,orderDto.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDto> orderDtos = orderService.findList(buyerOpenId, pageRequest);
        Assert.assertNotEquals(0,orderDtos.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDto orderDto = orderService.findOne(orderId);
        OrderDto result = orderService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDto orderDto = orderService.findOne(orderId);
        OrderDto result = orderService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDto orderDto = orderService.findOne(orderId);
        OrderDto result = orderService.paid(orderDto);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

    @Test
    public void list(){
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDto> orderDtos = orderService.findList(pageRequest);
        //Assert.assertNotEquals(0,orderDtos.getTotalElements());
        Assert.assertTrue("测试查询所有订单列表",orderDtos.getTotalElements() > 0);
    }

    @Test
    public void test(){
        for (int i = 0 ; i < 10 ; i++){
            redisTemplate.opsForSet().add("1234567",String.valueOf(222 + i));
        }
    }

    @Test
    public void test1(){
        Set<String> set = redisTemplate.opsForSet().members("1234567");
        log.info("【存入效果】size={},内容={}",set.size(),set.toString());
    }

}