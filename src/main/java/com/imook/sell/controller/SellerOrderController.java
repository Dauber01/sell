package com.imook.sell.controller;

import com.imook.sell.dto.OrderDto;
import com.imook.sell.service.OrderService;
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
        map.put("orderDtoPage",orderDtoPage);
        return new ModelAndView("order/list",map);
    }

}
