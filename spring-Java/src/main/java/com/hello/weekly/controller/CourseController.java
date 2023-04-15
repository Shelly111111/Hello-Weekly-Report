package com.hello.weekly.controller;

import com.hello.weekly.pojo.Course;
import com.hello.weekly.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public List<Course> course(@RequestParam(value = "technicalDirection") String technicalDirection) {

        List<Course> course = courseService.findCourse(technicalDirection);

        //如果查询到，返回该类型，否则为null
        if (course != null) {

            return course;
        }
        return null;
    }

}
