package com.imook.sell.dataobject.mapper;

import com.imook.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * to do
 *
 * @author Lucifer
 * @date $(DATE)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;
   /* @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;*/

    @Test
    public void insertByMap() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("category_name","男生最不爱");
        map.put("category_type",101);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObject() throws Exception{
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("师兄最不爱");
        productCategory.setCategoryType(103);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryType(){
        ProductCategory result = mapper.findByCategoryType(101);
        Assert.assertNotNull(result);
    }

    /**
     * 测试redis的两种对外接口是否共存以及相同key是否干涉
     */
   /* @Test
    public void redisTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("lucife");
        productCategory.setCategoryType(12);
        stringRedisTemplate.opsForValue().set("abcd","1234");
        redisTemplate.opsForValue().set("abcd",productCategory,600, TimeUnit.SECONDS);
        productCategory = (ProductCategory)redisTemplate.opsForValue().get("abcd");
        log.info("【对象存储】的:" + productCategory.toString());
        log.info("【string存储】的" + stringRedisTemplate.opsForValue().get("abcd"));
    }*/

}