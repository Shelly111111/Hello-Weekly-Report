package com.hello.weekly.service;

import com.hello.weekly.Res.ResponseCourse;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.pojo.Course;
import com.hello.weekly.pojo.UserCourse;

import java.util.List;

public interface UserCourseService {

    /***
     * 查找userid对应的courseids
     * @param userid
     * @return
     */

    List<UserCourse> findUserCourse(Integer userid);

    /***
     * 在course中查找对应id的课程
     * @param userCourses
     * @return
     */
    List<Course> findCourse(List<UserCourse> userCourses);


    /***
     *
     * @param pageNum
     * @param pageSize
     * @param userid
     * @return
     */
    ResponseCourse findListCourse(Integer pageNum, Integer pageSize,Integer userid);




}
