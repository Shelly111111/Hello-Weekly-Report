package com.hello.weekly.service;

import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.pojo.Course;

import java.util.List;

public interface CourseService {

    /***
     * 查找相应类课程
     */

    ResponsePage findCourse(Integer pageNum,Integer pageSize,String technicalDirection);

    Course selectCourse(Integer userid);

}
