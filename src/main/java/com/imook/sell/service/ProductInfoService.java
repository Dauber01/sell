package com.imook.sell.service;

import com.imook.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 * @author Lucifer
 * @date 2017/12/28 19:35
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll(Integer productStatus);

    List<ProductInfo> findAll(Pageable pageable);

    Pa
}
