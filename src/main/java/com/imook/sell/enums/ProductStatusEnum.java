package com.imook.sell.enums;
/**
 * 商品状态码
 * @author Lucifer
 * @date 2017／12／30 14:51
 */
public enum ProductStatusEnum {
    UP(0,"在架"),
    Down(1,"下架")
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

}
