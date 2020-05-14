package com.yishi.mall;

import com.alibaba.fastjson.JSON;
import com.yishi.mall.user.service.UserService;
import com.yishi.mall.vo.UserInfo;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserApplication.class)
@Slf4j
class UserTest {
  @Reference
//  @Resource
  private UserService userService;

  @Test
  void getUser() {
    UserInfo user = userService.getUser(1);
    log.info(JSON.toJSONString(user));
  }
}
