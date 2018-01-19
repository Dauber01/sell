package com.imook.sell.controller;

import com.imook.sell.constant.RedisConstant;
import com.imook.sell.dataobject.SellerInfo;
import com.imook.sell.enums.ResultEnum;
import com.imook.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户登陆
 * @author Lucifer
 * @date 2018/01/19 17:00
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid")String openid,
                              Map<String,Object> map){
        //1.根据openid去数据库中查找数据
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (null == sellerInfo){
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        //2.设置redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);
        //3.设置token至cookie

        return null;
    }

    @GetMapping("/logout")
    public ModelAndView logout(){
        return null;
    }

}
