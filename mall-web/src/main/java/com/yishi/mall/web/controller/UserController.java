package com.yishi.mall.web.controller;

import com.yishi.mall.order.service.OrderService;
import com.yishi.mall.user.service.UserService;
import com.yishi.mall.vo.UserInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/user")
public class UserController {
  @Reference private UserService userService;
  @Reference private OrderService orderService;

  @RequestMapping("/get")
  public UserInfo get(Integer id) {
    return userService.getUser(id);
  }

  @RequestMapping("/order")
  public int get() {
    return orderService.count();
  }
}
