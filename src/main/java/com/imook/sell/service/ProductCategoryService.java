package com.imook.sell.service;

import com.imook.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 * @author Lucifer
 * @date 2017/12/27 19:43
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
