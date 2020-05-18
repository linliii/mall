package com.yishi.mall.web.controller;

import com.yishi.mall.domain.ServiceException;
import com.yishi.mall.response.ResponseCodeEnum;
import com.yishi.mall.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;

@RestControllerAdvice
public class ExceptionController {

  private static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

  @ExceptionHandler(Exception.class)
  public Result processUnauthenticatedException(NativeWebRequest request, Exception e) {
    if (e instanceof ServiceException) {
      logger.warn("error msg:", e);
      return Result.fail((ServiceException) e);
    } else {
      logger.error("error msg:", e);
      return Result.fail(ResponseCodeEnum.SYSTEM_ERROR);
    }
  }
}
