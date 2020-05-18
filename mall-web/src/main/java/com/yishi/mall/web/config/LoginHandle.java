package com.yishi.mall.web.config;

import com.yishi.mall.domain.NeedLogin;
import com.yishi.mall.domain.ServiceException;
import com.yishi.mall.response.ResponseCodeEnum;
import com.yishi.mall.vo.UserInfo;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoginHandle {

  @Around(value = "@annotation(needLogin)")
  public Object loginForMethod(ProceedingJoinPoint joinPoint, NeedLogin needLogin) throws Throwable {
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = sra.getRequest();
    UserInfo user = (UserInfo) request.getAttribute("user");
    if (user == null) {
      throw new ServiceException(ResponseCodeEnum.NOT_LOGIN);
    }
    return joinPoint.proceed();
  }

  @Before("@within(com.yishi.mall.domain.NeedLogin)")
  public void loginForClass(JoinPoint joinPoint) throws ClassNotFoundException {
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = sra.getRequest();
    UserInfo user = (UserInfo) request.getAttribute("user");
    if (user == null) {
      throw new ServiceException(ResponseCodeEnum.NOT_LOGIN);
    }
  }
}
