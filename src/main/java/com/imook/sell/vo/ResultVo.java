package com.imook.sell.vo;

import lombok.Data;

/**
 * http请求返给前端的最外层对象
 * @author Lucifer
 * @date 2017／12／30 15:43
 */
@Data
public class ResultVo<T> {
    /** 错误码. */
    private Integer code;
    /** 错误原因. */
    private String msg;
    /** 具体内容. */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
