package com.imook.sell.form;

import lombok.Data;

/**
 * 商品类目form
 * @author Lucifer
 * @date 2018／01／18 22:39
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

}
