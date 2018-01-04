package com.imook.sell.controller;

import com.imook.sell.config.WechatMpConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信服务接口
 * @author Lucifer
 * @date 2018/01/04 20:23
 */
@RestController
@Slf4j
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public void authorize(@RequestParam("returnUrl")String returnUrl){
        String url = "http://localhost:8080/sell/wechat/userInfo";
        String result = wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAUTH2_SCOPE_USER_INFO);
        log.info("【微信网页授权】获取code,result = {}",result);
    }

    @GetMapping("/userInfo")
    public void userInfo(){

    }
}
