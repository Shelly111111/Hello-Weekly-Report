package com.hello.weekly.service.impl;

import com.hello.weekly.mapper.CourseMapper;
import com.hello.weekly.mapper.UserCourseMapper;
import com.hello.weekly.pojo.Course;
import com.hello.weekly.pojo.UserCourse;
import com.hello.weekly.pojo.UserCourse1;
import com.hello.weekly.service.UserCourseService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCourseService1Impl implements UserCourseService1 {


    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<UserCourse1> selectAllUserCourse(List<UserCourse> userCourses) {
        List<UserCourse1> userCourse1s = new ArrayList<>();
        for (UserCourse userCourse : userCourses
        ) {
            Course course = courseMapper.selectById(userCourse.getCourseId());
                UserCourse1 a = new UserCourse1(userCourse, course.getTitle(), course.getDescription(), course.getDateTime(), course.getTechnicalDirection(), course.getLevel(), course.getMode());
                userCourse1s.add(a);

        }

        if(userCourse1s == null)
            return null;
        return userCourse1s;

    }
}
