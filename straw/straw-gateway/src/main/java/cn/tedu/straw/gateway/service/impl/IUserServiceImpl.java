package cn.tedu.straw.gateway.service.impl;

import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.gateway.mapper.UserMapper;
import cn.tedu.straw.gateway.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserInfo(String username) {
        return userMapper.findByUsername(username);
    }
}
