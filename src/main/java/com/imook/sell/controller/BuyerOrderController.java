package com.imook.sell.controller;

import com.imook.sell.enums.ResultEnum;
import com.imook.sell.exception.SellException;
import com.imook.sell.form.OrderForm;
import com.imook.sell.service.OrderService;
import com.imook.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * 买家订单
 * @author Lucifer
 * @date 2018/01/02 11:06
 */
@RestController
@Slf4j
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;

    //创建订单
     public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult  bindingResult){
         if (bindingResult.hasErrors()){
             log.error("【创建订单】参数不正确,orderForm = {}",orderForm);
             throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
         }

         orderService.create();
         return null;
     }
    //订单列表

    //订单详情

    //取消订单

}
