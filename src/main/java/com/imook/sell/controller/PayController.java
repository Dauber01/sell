package com.imook.sell.controller;

import com.imook.sell.dto.OrderDto;
import com.imook.sell.enums.ResultEnum;
import com.imook.sell.exception.SellException;
import com.imook.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 支付接口
 * @author Lucifer
 * @date 2018/01/09 16:43
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId")String orderId,
                            @RequestParam("returnUrl")String returnUrl){
        OrderDto orderDto = orderService.findOne(orderId);
        if (null == orderDto){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

    }

}
