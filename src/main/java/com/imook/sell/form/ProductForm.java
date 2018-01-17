package com.imook.sell.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 卖家商品表单对象
 * @author Lucifer
 * @date 2018／01／18 00:22
 */
@Data
public class ProductForm {
    /** 商品id. */
    private String productId;

    /** 商品名字. */
    private String productName;

    /** 商品单价. */
    private BigDecimal productPrice;

    /** 商品库存. */
    private Integer productStock;

    /** 商品描述. */
    private String productDescription;

    /** 商品小图. */
    private String productIcon;

    /** 类目编号. */
    private Integer categoryType;
}
