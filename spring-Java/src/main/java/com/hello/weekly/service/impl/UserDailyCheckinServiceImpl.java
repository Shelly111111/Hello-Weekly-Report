package com.hello.weekly.service.impl;

import com.hello.weekly.mapper.UserDailyCheckinMapper;
import com.hello.weekly.pojo.UserDailyCheckin;
import com.hello.weekly.service.UserDailyCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDailyCheckinServiceImpl implements UserDailyCheckinService {
    @Autowired
    private UserDailyCheckinMapper userDailyCheckinMapper;


    @Override
    public List<UserDailyCheckin> getAllByUserId(Integer user_id) {
        return  userDailyCheckinMapper.getAllByUserId(user_id);
    }
}