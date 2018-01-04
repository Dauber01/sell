package com.imook.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 微信接口
 * @author Lucifer
 * @date 2018/01/04 17:07
 */
@RestController
@Slf4j
@RequestMapping("/weixin")
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code")String code){
        log.info("进入auth方法...");
        log.info("code = {}",code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx53cd4b9fbb3c66cf&secret=146bcc37544928f63594e4a2526051e4&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url,String.class);
    }

}
