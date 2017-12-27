package com.imook.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类目
 * @author Lucifer
 * @date 2017/12/27 10:56
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    /** 类目id. */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

   /* *//** 创建时间. *//*
    private Date createTime;

    */

    public ProductCategory() {
    }

    /** 创建时间. *//*
    private Date updateTime;
*/

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
