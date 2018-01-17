package com.imook.sell.enums;

import lombok.Getter;

/**
 * 商品状态码
 * @author Lucifer
 * @date 2017／12／30 14:51
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0,"在架"),
    Down(1,"下架")
    ;

    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
