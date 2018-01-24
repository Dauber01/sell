package com.imook.sell.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.imook.sell.dataobject.ProductCategory;
import com.imook.sell.dataobject.ProductInfo;
import com.imook.sell.enums.ProductStatusEnum;
import com.imook.sell.service.ProductCategoryService;
import com.imook.sell.service.ProductInfoService;
import com.imook.sell.util.ResultVoUtil;
import com.imook.sell.vo.ProductInfoVo;
import com.imook.sell.vo.ProductVo;
import com.imook.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * @author Lucifer
 * @date 2017／12／30 15:37
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key = "#sellerId",condition = "#sellerId.length() > 3",unless = "#result.code != 0")
    public ResultVo list(@RequestParam("sellerId") String sellerId){
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVo> productVoList = new ArrayList<ProductVo>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<ProductInfoVo>();
            for (ProductInfo productInfo : productInfoList){
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVoUtil.success(productVoList);
    }

}
