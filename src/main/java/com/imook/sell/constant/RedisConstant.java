package com.imook.sell.constant;

/**
 * redis常量
 * @author Lucifer
 * @date 2018/01/19 17:31
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";

    /** 过期时间2小时. */
    Integer EXPIRE = 7200;

}
