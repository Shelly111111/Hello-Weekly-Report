package com.hello.weekly.service;

import com.hello.weekly.pojo.UserCourse;
import com.hello.weekly.pojo.UserCourse1;

import java.util.List;

public interface UserCourseService1 {
    /***
     * 查找
     */
    List<UserCourse1> selectAllUserCourse(List<UserCourse> userCourses);
}
