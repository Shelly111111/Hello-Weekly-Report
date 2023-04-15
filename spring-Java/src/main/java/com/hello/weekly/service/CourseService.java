package com.hello.weekly.service;

import com.hello.weekly.pojo.Course;

import java.util.List;

public interface CourseService {

    /***
     * 查找相应类课程
     */
    List<Course> findCourse(String technicalDirection);

}
