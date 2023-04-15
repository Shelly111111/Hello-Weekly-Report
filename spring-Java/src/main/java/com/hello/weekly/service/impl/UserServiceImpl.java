package com.hello.weekly.service.impl;

import com.hello.weekly.mapper.UserMapper;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入信息到数据库中
     *
     * @param user com.hello.weekly.pojo.User
     * @return
     */
    @Override
    public Integer insertUser(User user) {
        return userMapper.insert(user);
    }

    /**
     * 通过用户名进行相关用户的查询
     *
     * @param username String
     * @return com.hello.weekly.pojo.User
     */
    @Override
    public User findByUsername(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<User> users = userMapper.selectByMap(map);
        if (users.isEmpty()) return null;
        return users.get(0);
    }

}