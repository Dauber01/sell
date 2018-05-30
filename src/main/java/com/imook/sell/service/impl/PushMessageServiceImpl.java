package com.imook.sell.service.impl;

import com.imook.sell.config.WechatAccountConfig;
import com.imook.sell.dto.OrderDto;
import com.imook.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 消息推送服务
 * @author Lucifer
 * @date 2018／01／22 22:06
 */
@Slf4j
@Service
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDto orderDto) {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().build();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDto.getBuyerOpenid());
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first","亲，记得收货哦～"),
                new WxMpTemplateData("keyword1","微信点餐"),
                new WxMpTemplateData("keyword2","15004011234"),
                new WxMpTemplateData("keyword3",orderDto.getOrderId()),
                new WxMpTemplateData("keyword4",orderDto.getOrderStatusEnum().getMsg()),
                new WxMpTemplateData("keyword5","¥" + orderDto.getOrderAmount()),
                new WxMpTemplateData("remark","欢迎再次光临")
        );
        for (WxMpTemplateData wxMpTemplateData : wxMpTemplateDataList){
            templateMessage.addWxMpTemplateData(wxMpTemplateData);
        }
        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e){
            log.error("【微信模版消息】发送失败,{}",e);
        }
    }
}
