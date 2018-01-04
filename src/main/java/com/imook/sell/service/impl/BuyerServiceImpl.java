package com.imook.sell.service.impl;

import com.imook.sell.dto.OrderDto;
import com.imook.sell.enums.ResultEnum;
import com.imook.sell.exception.SellException;
import com.imook.sell.service.BuyerService;
import com.imook.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 买家服务类
 * @author Lucifer
 * @date 2018/01/04 14:30
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid, orderId);
        if (null == orderDto){
            log.error("【取消订单】查不到该订单,orderId = {}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return  orderService.cancel(orderDto);
    }

    private  OrderDto checkOrderOwner(String openid, String orderId) {
        OrderDto orderDto = orderService.findOne(orderId);
        if (null == orderDto){
            return null;
        }
        if (!openid.equalsIgnoreCase(orderDto.getOrderId())){
            log.error("【查询订单】订单的openid不一致,openid = {},orderDto = {}",openid,orderDto);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
