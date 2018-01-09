package com.imook.sell.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 涉及到json类型的转换
 * @author Lucifer
 * @date 2018/01/09 17:50
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

}
