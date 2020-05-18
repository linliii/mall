package com.yishi.mall.response;

import com.yishi.mall.domain.ServiceException;
import java.io.Serializable;

/**
 * 响应对象
 */
@SuppressWarnings("rawtypes")
public class Result<T> implements Serializable, Cloneable {

  private static final long serialVersionUID = 8000349409315221668L;
  private int code;
  private String msg;
  private T data;

  public static Result fail(ResponseCodeEnum responseCodeEnum) {
    Result result = new Result();
    result.code = responseCodeEnum.getCode();
    result.msg = responseCodeEnum.getMsg();
    return result;
  }

  public static Result fail(ServiceException exception) {
    Result result = new Result();
    result.code = exception.getCode();
    result.msg = exception.getMsg();
    return result;
  }

  public static Result fail(int code, String errorMessage) {
    Result result = new Result();
    result.code = code;
    result.msg = errorMessage;
    return result;
  }

  public static Result fail(String errorMessage) {
    Result result = new Result();
    result.code = ResponseCodeEnum.SYSTEM_ERROR.getCode();
    result.msg = errorMessage;
    return result;
  }

  public static <T> Result success(T data) {
    Result<T> result = new Result<>();
    result.msg = ResponseCodeEnum.SUCCESS.getMsg();
    result.data = data;
    result.code = ResponseCodeEnum.SUCCESS.getCode();
    return result;
  }

  public static Result success() {
    Result result = new Result();
    result.msg = ResponseCodeEnum.SUCCESS.getMsg();
    result.code = ResponseCodeEnum.SUCCESS.getCode();
    return result;
  }

  public Object getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }


}
