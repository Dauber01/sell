package com.imook.sell.enums;

import lombok.Data;

/**
 * 订单状态
 * @author Lucifer
 * @date 2017／12／30 21:26
 */
public enum OrderStatusEnum {

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),
    ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
