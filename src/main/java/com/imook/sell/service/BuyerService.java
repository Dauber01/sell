package com.imook.sell.service;

import com.imook.sell.dto.OrderDto;

/**
 * 买家服务
 * @author Lucifer
 * @date 2018/01/04 14:27
 */
public interface BuyerService{

    OrderDto findOrderOne(String openid, String orderId);

    OrderDto cancelOrder(String openid, String orderId);

}
