package com.imook.sell.service;

import com.imook.sell.dto.OrderDto;

/**
 * 消息推送服务
 * @author Lucifer
 * @date 2018／01／22 21:57
 */
public interface PushMessageService {

    /**
     * 订单状态变更通知
     * @param orderDto
     */
    void orderStatus(OrderDto orderDto);

}
