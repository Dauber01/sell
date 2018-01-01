package com.imook.sell.enums;

import lombok.Getter;

/**
 * 支付状态
 * @author Lucifer
 * @date 2017／12／30 21:36
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"已支付"),
    ;

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
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
