package com.imook.sell.repository;

import com.imook.sell.dataobject.ProductCategory;
import com.imook.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品
 * @author Lucifer
 * @date 2017／12／27 23:26
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(String productStatus);
}
