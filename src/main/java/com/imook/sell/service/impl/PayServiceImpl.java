package com.imook.sell.service.impl;

import com.imook.sell.dto.OrderDto;
import com.imook.sell.service.PayService;
import com.imook.sell.util.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付
 *
 * @author Lucifer
 * @date 2018/01/09 16:55
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService{

    private final String PAY_NAME = "微信点餐支付";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Override
    public void create(OrderDto orderDto) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDto.getOrderId());
        payRequest.setOrderName(PAY_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】payRequest = {}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】payResponse = {}",JsonUtil.toJson(payResponse));

    }
}
