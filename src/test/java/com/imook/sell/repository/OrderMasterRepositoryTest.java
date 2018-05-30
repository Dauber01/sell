package com.imook.sell.repository;

import com.imook.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final String OPENID = "110110";

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerAddress("江西");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("15004011230");
        orderMaster.setOrderAmount(new BigDecimal(23));
        orderMaster.setOrderId("123457");

        OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(orderMaster1);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(0,3);
        Page<OrderMaster> orderMasterList = orderMasterRepository.findByBuyerOpenid(OPENID, pageRequest);
        Assert.assertNotEquals(0,orderMasterList.getTotalElements());
    }

}