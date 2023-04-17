package com.hello.weekly.service;

import com.hello.weekly.pojo.UserInfo;

/**
 * @discription: 用户信息服务
 * @author: Zhang
 * @date: 2023/4/14
 */
public interface UserInfoService {

    /**
     * 插入一条信息到数据库中
     * @param userInfo com.hello.weekly.pojo.UserInfo
     * @return
     *
     * @author: Zhang
     * @date: 2023/4/14
     */
    Integer insertUserInfo(UserInfo userInfo);

    /**
     * 根据uid查找用户信息
     * @param uid 用户id
     * @return com.hello.weekly.pojo.UserInfo
     *
     * @author: Zhang
     * @date: 2023/4/14
     */
    UserInfo findByUid(Integer uid);

    /**
     * 更新用户信息
     * @param userInfo com.hello.weekly.pojo.UserInfo 用户信息
     * @return
     *
     * @author: Zhang
     * @date: 2023/4/14
     */
    Integer updateUserInfo(UserInfo userInfo);
}
