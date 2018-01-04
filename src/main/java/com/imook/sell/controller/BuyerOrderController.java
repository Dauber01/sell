package com.imook.sell.controller;

import com.imook.sell.converter.OrderFormToOrderDtoConverter;
import com.imook.sell.dto.OrderDto;
import com.imook.sell.enums.ResultEnum;
import com.imook.sell.exception.SellException;
import com.imook.sell.form.OrderForm;
import com.imook.sell.service.BuyerService;
import com.imook.sell.service.OrderService;
import com.imook.sell.util.ResultVoUtil;
import com.imook.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
     public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult  bindingResult){
         if (bindingResult.hasErrors()){
             log.error("【创建订单】参数不正确,orderForm = {}",orderForm);
             throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
         }
         OrderDto orderDto = OrderFormToOrderDtoConverter.converter(orderForm);
         if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
             log.error("【创建订单】参数不正确,orderDetail = {}",orderDto.getOrderDetailList());
             throw new SellException(ResultEnum.CART_EMPTY);
         }
         OrderDto createResult = orderService.create(orderDto);
         Map<String,String> map = new HashMap<String, String>();
         map.put("orderId",createResult.getOrderId());
         return ResultVoUtil.success(map);
     }

    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDto>> list(@RequestParam("openid")String openid,
                                         @RequestParam(value = "page", defaultValue = "0")Integer page,
                                         @RequestParam(value = "size", defaultValue = "10")Integer size){
         if (StringUtils.isEmpty(openid)){
             log.error("【查询订单列表】openid为空");
             throw new SellException(ResultEnum.PARAM_ERROR);
         }
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderDto> result = orderService.findList(openid,pageRequest);
        return ResultVoUtil.success(result.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("openid")String openid,
                                           @RequestParam("orderId")String orderId){
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)){
            log.error("【查询订单详情】参数为空,openid = {},orderId = {}",openid,orderId);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDto orderDto = buyerService.findOrderOne(openid,orderId);
        return ResultVoUtil.success(orderDto);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid")String openid,
                           @RequestParam("orderId")String orderId){
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)){
            log.error("【查询订单详情】参数为空,openid = {},orderId = {}",openid,orderId);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        buyerService.cancelOrder(openid,orderId);
        return ResultVoUtil.success();
    }
}
