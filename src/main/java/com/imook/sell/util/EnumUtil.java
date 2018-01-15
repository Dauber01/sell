package com.imook.sell.util;

import com.imook.sell.enums.CodeEnum;

/**
 * 枚举工具类
 * @author Lucifer
 * @date 2018／01／15 23:12
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClasses){
        for (T each : enumClasses.getEnumConstants()){
            if (each.getCode().equals(code)){
                return each;
            }
        }
        return null;
    }

}
