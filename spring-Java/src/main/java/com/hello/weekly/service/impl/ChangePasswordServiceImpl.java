package com.hello.weekly.service.impl;

import com.hello.weekly.mapper.UserMapper;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户名进行相关用户的查询
     *
     * @param user com.hello.weekly.pojo.User
     * @return
     */

    @Override
    public Integer changePassword(User user) {

        return userMapper.updateById(user);
    }
}
