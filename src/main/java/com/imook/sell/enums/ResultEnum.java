package com.imook.sell.enums;

import lombok.Getter;

/**
 * 返回结果枚举
 * @author Lucifer
 * @date 2017／12／31 11:58
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"找不到商品"),
    PRODUCT_STOCK_ERROR(11,"库存不足异常"),
    ORDER_NOT_EXIST(12,"找不到订单"),
    ORDERDETAIL_NOT_EXIST(13,"查无订单详情"),
    ORDER_STATUS_ERROR(14,"订单状态错误"),
    ORDER_UPDATE_FAIL(15,"订单状态更新失败"),
    ORDER_DETAIL_ENTITY(16,"无订单详情"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),
    PARAM_ERROR(18,"参数错误"),
    CART_EMPTY(19,"购物车为空"),
    ORDER_OWNER_ERROR(20,"该订单不属于当前用户"),
    WECHAT_MP_ERROR(21,"微信获取openid过程异常"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
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
