package com.hello.weekly.service;

import com.hello.weekly.pojo.UserDailyCheckin;

import java.util.List;

public interface UserDailyCheckinService {
    // 根据用户ID和日期查询打卡记录
    public List<UserDailyCheckin> getAllByUserId(Integer user_id);

}
