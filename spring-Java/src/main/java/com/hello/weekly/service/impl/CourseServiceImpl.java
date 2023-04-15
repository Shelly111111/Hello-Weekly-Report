package com.hello.weekly.service.impl;

import com.hello.weekly.mapper.CourseMapper;
import com.hello.weekly.pojo.Course;
import com.hello.weekly.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /***
     * 从数据库中选择信息
     */
    @Override
    public List<Course> findCourse(String technicalDirection){
        Map<String, Object> map = new HashMap<>();
        map.put("technicalDirection", technicalDirection);
        List<Course> course = courseMapper.selectByMap(map);
        if (course.isEmpty()) return null;
        return course;
    }

}
