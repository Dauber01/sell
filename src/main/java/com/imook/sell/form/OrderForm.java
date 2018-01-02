package com.imook.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 订单表单验证
 * @author Lucifer
 * @date 2018/01/02 11:11
 */
@Data
public class OrderForm {

    /** 买家姓名. */
    @NotEmpty(message = "姓名为必填")
    private String name;

    /** 买家手机号. */
    @NotEmpty(message = "手机号为必填")
    private String phone;

    /** 买家地址. */
    @NotEmpty(message = "地址为必填")
    private String address;

    /** 买家微信openid. */
    @NotEmpty(message = "openid为必填")
    private String openid;

    /** 购物车信息. */
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
