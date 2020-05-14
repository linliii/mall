package com.yishi.mall.user.service;

import com.yishi.mall.user.mapper.UserMapper;
import com.yishi.mall.vo.UserInfo;
import javax.annotation.Resource;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;

@Service
public class UserServiceImpl implements UserService {
  @Resource private UserMapper userMapper;

  @Override
  public UserInfo getUser(Integer id) {
    UserInfo userInfo = new UserInfo();
    BeanUtils.copyProperties(userMapper.getById(id), userInfo);
    return userInfo;
  }
}
