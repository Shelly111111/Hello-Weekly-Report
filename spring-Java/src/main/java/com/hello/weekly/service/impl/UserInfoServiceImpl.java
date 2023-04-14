package com.hello.weekly.service.impl;

import com.hello.weekly.mapper.UserInfoMapper;
import com.hello.weekly.pojo.UserInfo;
import com.hello.weekly.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    /**
     * 插入一条信息到数据库中
     * @param userInfo com.hello.weekly.pojo.UserInfo
     * @return
     *
     * @author: Zhang
     * @date: 2023/4/14
     */
    @Override
    public Integer insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    /**
     * 根据uid查找用户信息
     * @param uid 用户id
     * @return com.hello.weekly.pojo.UserInfo
     *
     * @author: Zhang
     * @date: 2023/4/14
     */
    @Override
    public UserInfo findByUid(Integer uid) {
        return userInfoMapper.selectById(uid);
    }

    /**
     * 更新用户信息
     * @param userInfo com.hello.weekly.pojo.UserInfo 用户信息
     * @return
     *
     * @author: Zhang
     * @date: 2023/4/14
     */
    @Override
    public Integer updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateById(userInfo);
    }
}
