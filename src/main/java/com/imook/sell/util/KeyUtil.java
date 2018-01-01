package com.imook.sell.util;

import java.util.Random;

/**生成主键
 * @author Lucifer
 * @date 2017／12／31 12:12
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式:时间毫秒数+六位随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
