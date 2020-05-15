package com.yishi.mall;

import com.alibaba.fastjson.JSON;
import com.yishi.mall.utils.RedisUtils;
import com.yishi.mall.user.model.User;
import com.yishi.mall.user.service.UserService;
import com.yishi.mall.vo.UserInfo;
import java.time.LocalDateTime;
import java.util.Date;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserApplication.class)
@Slf4j
class UserTest {
  @Reference private UserService userService;
  @Resource private RedisUtils redisUtils;

  @Test
  void getUser() {
    UserInfo user = userService.getUser(1);
    log.info(JSON.toJSONString(user));
  }

  @Test
  public void testRedis() {
    System.out.println("---------------");
    redisUtils.set("test", "string");
    String str = redisUtils.get("test");
    System.out.println(str);
    User user = new User();
    user.setId(1);
    user.setNickName("rp");
    user.setCreateTime(new Date());
    user.setUpdateTime(LocalDateTime.now());
    redisUtils.set("test", user);
    User obj = redisUtils.get("test");
    System.out.println(JSON.toJSONString(obj));
    System.out.println("============");
  }
}
