package com.imook.sell.dataobject.dao;

import com.imook.sell.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 基于mybatis的商品类目操作的dao层
 * @author Lucifer
 * @date 2018/01/24 10:43
 */
public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper mapper;

    int insertByMap(Map<String,Object> map){
        return mapper.insertByMap(map);
    }

}
