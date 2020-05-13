package com.yishi.mall;

import com.alibaba.fastjson.JSON;
import com.yishi.mall.user.service.UserService;
import com.yishi.mall.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = UserApplication.class)
@Slf4j
class UserTest {
    @Resource
    private UserService userService;

    @Test
    void getUser() {
        UserInfo user = userService.getUser(1);
        log.info(JSON.toJSONString(user));
    }
}
