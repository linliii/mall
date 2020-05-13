package com.yishi.mall.user.service;

import com.yishi.mall.vo.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
//    @Resource
//    private UserMapper userMapper;
//
//    public UserInfo getUser(Integer id) {
//        UserInfo userInfo = new UserInfo();
//        BeanUtils.copyProperties(userMapper.getById(id), userInfo);
//        return userInfo;
//    }
    @Override
    public UserInfo getUser(Integer id) {
        return new UserInfo();
    }
}