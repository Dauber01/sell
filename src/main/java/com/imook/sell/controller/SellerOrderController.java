package com.imook.sell.controller;

import com.imook.sell.dto.OrderDto;
import com.imook.sell.enums.ResultEnum;
import com.imook.sell.exception.SellException;
import com.imook.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家订单
 * @author Lucifer
 * @date 2018／01／14 15:59
 */
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * @param page 列表的页码
     * @param size 页宽
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page - 1,size);
        Page<OrderDto> orderDtoPage = orderService.findList(pageRequest);
        orderDtoPage.getTotalPages();
        map.put("orderDtoPage",orderDtoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }

    /**
     * 卖家端取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId")String orderId,
                               Map<String,Object> map){
        try{
            OrderDto orderDto = orderService.findOne(orderId);
            orderService.cancel(orderDto);
        }catch (SellException e){
            log.error("【卖家端取消订单】发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCECESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 卖家端查询订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId,
                               Map<String,Object> map){
        OrderDto orderDto = new OrderDto();
        try {
            orderDto = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("【卖家端查询订单详情】发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDto",orderDto);
        return new ModelAndView("order/detail",map);
    }

    /**
     * 卖家完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId,
                               Map<String,Object> map){
        OrderDto orderDto = new OrderDto();
        try{
            orderDto = orderService.findOne(orderId);
            orderService.finish(orderDto);
        }catch (SellException e){
            log.error("【卖家端完结订单】发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
