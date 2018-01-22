package com.imook.sell.aspect;

import com.imook.sell.constant.CookieConstant;
import com.imook.sell.exception.SellAuthorizeException;
import com.imook.sell.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 登陆aop类
 * @author Lucifer
 * @date 2018/01/22 17:29
 */
@Slf4j
@Aspect
@Component
public class SellerAuthorizeAspect {

    @Pointcut("execution(public * com.imook.sell.controller.Seller*.*(..))" +
    "&& !execution(public * com.imook.sell.controller.SellerUserController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null){
            log.warn("【登陆校验】,cookie中查询不到token");
            throw new SellAuthorizeException();
        }
    }

}
