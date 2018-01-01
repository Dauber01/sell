package com.imook.sell.dto;

/**
 * 购物车
 * @author Lucifer
 * @date 2017／12／31 14:51
 */
public class CartDTO {

    /** 商品id. */
    private String productId;

    /** 商品库存. */
    private Integer productStock;

    public CartDTO(String productId, Integer productStock) {
        this.productId = productId;
        this.productStock = productStock;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }
}
