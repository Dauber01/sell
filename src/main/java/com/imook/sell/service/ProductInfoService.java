package com.imook.sell.service;

import com.imook.sell.dataobject.ProductInfo;
import com.imook.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 * @author Lucifer
 * @date 2017/12/28 19:35
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDTOList
     * @return
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList
     * @return
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}
