package com.yishi.mall.domain;

import com.yishi.mall.response.ResponseCodeEnum;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

  private int code;
  private String msg;

  public ServiceException(ResponseCodeEnum responseCodeEnum) {
    this.code = responseCodeEnum.getCode();
    this.msg = responseCodeEnum.getMsg();
  }

  public ServiceException(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public static ServiceException exception(ResponseCodeEnum responseCodeEnum) {
    return new ServiceException(responseCodeEnum);
  }

  public static ServiceException exception(int code, String msg) {
    return new ServiceException(code, msg);
  }

  public static ServiceException exception(String msg) {
    return new ServiceException(ResponseCodeEnum.SYSTEM_ERROR.getCode(), msg);
  }
}
