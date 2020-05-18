package com.yishi.mall.web.controller;

import com.yishi.mall.domain.NeedLogin;
import com.yishi.mall.order.service.OrderService;
import com.yishi.mall.user.service.UserService;
import com.yishi.mall.vo.UserInfo;
import java.time.LocalDate;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/user")
public class UserController {

  @Reference
  private UserService userService;
  @Reference
  private OrderService orderService;

  @RequestMapping("/get")
  public UserInfo get(Integer id) {
    return userService.getUser(id);
  }

  @NeedLogin
  @RequestMapping("/order")
  public int get(LocalDate now) {
    return orderService.count();
  }
}

