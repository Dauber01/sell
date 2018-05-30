package com.imook.sell.repository;

import com.imook.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234568");
        orderDetail.setOrderId("110110");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("123456");
        orderDetail.setProductName("西红柿鸡蛋汤");
        orderDetail.setProductPrice(new BigDecimal(10.3));
        orderDetail.setProductQuantity(20);

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("110110");
        Assert.assertNotEquals(0,orderDetailList.size());
    }

}