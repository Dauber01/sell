package com.imook.sell.service;

import com.imook.sell.dto.OrderDto;

/**
 * 支付
 * @author Lucifer
 * @date 2018/01/09 16:53
 */
public interface PayService {

    void create(OrderDto orderDto);
}
