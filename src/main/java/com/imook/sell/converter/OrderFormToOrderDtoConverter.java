package com.imook.sell.converter;

import com.imook.sell.dto.OrderDto;
import com.imook.sell.form.OrderForm;

/**
 * orderForm to orderDto
 *
 * @author Lucifer
 * @date 2018/01/02 11:29
 */
public class OrderFormToOrderDtoConverter {
    public static OrderDto converter(OrderForm orderForm){
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerAddress(orderForm.getPhone());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        orderDto.setOrderDetailList();
    }
}
