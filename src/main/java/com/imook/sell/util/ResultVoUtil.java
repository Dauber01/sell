package com.imook.sell.util;

import com.imook.sell.enums.ProductStatusEnum;
import com.imook.sell.vo.ResultVo;

/**
 * 返回值的封装
 * @author Lucifer
 * @date 2017／12／30 20:38
 */
public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ProductStatusEnum.UP.getCode());
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return resultVo;
    }

    public static ResultVo success(){
        return success(null);
    }

    public static ResultVo error(Integer code,String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }
}
