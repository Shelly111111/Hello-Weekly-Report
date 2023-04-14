package com.hello.weekly.service;

import com.hello.weekly.pojo.User;

public interface ChangePasswordService {

    /***
     * 修改密码
     */
    Integer changePassword(User user);
}
