package com.hello.oauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hello.oauth.mapper.UserMapper;
import com.hello.oauth.pojo.User;
import com.hello.oauth.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     *
     * @author: Zhang
     * @updatedate: 2023/4/18
     */
    @Override
    public User findByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(username), User::getUsername, username);
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 返回所有用户
     * @return 用户列表
     *
     * @author: Zhang
     * @date: 2023/4/18
     */
    public List<User> findAll() {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userMapper.selectByMap(map);
        return users;
    }
}