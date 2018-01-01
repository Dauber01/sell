package com.imook.sell.converter;

import com.imook.sell.dataobject.OrderMaster;
import com.imook.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单转化为订单dto类
 * @author Lucifer
 * @date 2017／12／31 16:54
 */
public class OrderMasterToOrderDtoConverter {

    public static OrderDto converter(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> converter(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(
                e -> converter(e)
        ).collect(Collectors.toList());
    }
}
