package com.hello.weekly.service;

import com.hello.weekly.pojo.User;

public interface UserService {


    /**
     * 插入一条信息到数据库中
     * @param user com.hello.weekly.pojo.User
     * @return
     */
    Integer insertUser(User user);


    /**
     * 通过用户名进行查询对应的用户
     * @param username String
     * @return
     */
    User findByUsername(String username);
}