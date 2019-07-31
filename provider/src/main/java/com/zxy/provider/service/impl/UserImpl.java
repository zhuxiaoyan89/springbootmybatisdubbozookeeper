package com.zxy.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zxy.common.domain.User;
import com.zxy.common.service.UserService;
import com.zxy.provider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class UserImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser(Long id) {
        return userMapper.getOne(id);
    }
}
