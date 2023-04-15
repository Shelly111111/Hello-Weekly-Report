package com.hello.weekly.service.impl;

import com.hello.weekly.mapper.UserCourseMapper;
import com.hello.weekly.pojo.UserCourse;
import com.hello.weekly.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    private UserCourseMapper userCourseMapper;


    @Override
    public List<UserCourse> findUserCourse(Integer userid){

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userid);
        List<UserCourse> userCourses = userCourseMapper.selectByMap(map);
        if(userCourses == null)
            return null;
        return userCourses;
    }


}
