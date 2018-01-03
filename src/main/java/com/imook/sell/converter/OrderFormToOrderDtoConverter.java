package com.imook.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imook.sell.dataobject.OrderDetail;
import com.imook.sell.dto.OrderDto;
import com.imook.sell.enums.ResultEnum;
import com.imook.sell.exception.SellException;
import com.imook.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * orderForm to orderDto
 *
 * @author Lucifer
 * @date 2018/01/02 11:29
 */
@Slf4j
public class OrderFormToOrderDtoConverter {
    public static OrderDto converter(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        try{
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】错误,string = {}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
