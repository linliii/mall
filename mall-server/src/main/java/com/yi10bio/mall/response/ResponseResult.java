package com.yi10bio.mall.response;

import java.io.Serializable;

/**
 * 响应对象
 */
public class ResponseResult implements Serializable, Cloneable {

    private static final long serialVersionUID = 8000349409315221668L;

    private String msg;

    private String code;

    private Object data;


    public static ResponseResult fail(String code,String errorMessage) {
        ResponseResult result = new ResponseResult();
        result.code = code;
        result.msg = errorMessage;
        return result;
    }

    public static ResponseResult success(Object data) {
        ResponseResult result = new ResponseResult();
        result.msg = ResponseCodeEnum.SUCCESS.getMsg();
        result.data = data;
        result.code = ResponseCodeEnum.SUCCESS.getCode();
        return result;
    }

    public static ResponseResult success() {
        ResponseResult result = new ResponseResult();
        result.msg = ResponseCodeEnum.SUCCESS.getMsg();
        result.code = ResponseCodeEnum.SUCCESS.getCode();
        return result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
