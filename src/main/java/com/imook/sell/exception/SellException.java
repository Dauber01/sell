package com.imook.sell.exception;

import com.imook.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * 商品异常
 * @author Lucifer
 * @date 2017／12／31 11:57
 */
@Getter
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String msg) {
        super(msg);
        this.code = code;
    }
}
