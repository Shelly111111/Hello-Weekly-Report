package com.hello.oauth.service;

import com.hello.oauth.pojo.User;

import java.util.List;

public interface UserService {


    /**
     * 插入一条信息到数据库中
     * @param user com.hello.weekly.pojo.User
     * @return
     *
     * @author: 漫舞枪神
     */
    Integer insertUser(User user);


    /**
     * 通过用户名进行查询对应的用户
     * @param username String
     * @return com.hello.weekly.pojo.User
     *
     * @author: 漫舞枪神
     * @updatedate: 2023/4/18
     */
    User findByUsername(String username);

    /**
     * 返回所有用户
     * @return 用户列表
     *
     * @author: 漫舞枪神
     * @date: 2023/4/18
     */
    List<User> findAll();
}
