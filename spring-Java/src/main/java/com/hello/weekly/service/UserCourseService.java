package com.hello.weekly.service;

import com.hello.weekly.pojo.UserCourse;
import com.hello.weekly.pojo.UserCourse1;

import java.util.List;

public interface UserCourseService {

    /***
     * 查找userid对应的courseids
     * @param userid
     * @return
     */
    List<UserCourse> findUserCourse(Integer userid);


}
