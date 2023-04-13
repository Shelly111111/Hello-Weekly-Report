package com.hello.weekly.service.impl;

import com.hello.weekly.mapper.UserMapper;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入信息到数据库中
     * @param user
     * @return
     */
    @Override
    public Integer insertUser(User user) {
        return userMapper.insertUser(user);
    }

    /**
     * 通过用户名进行相关用户的查询
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

}