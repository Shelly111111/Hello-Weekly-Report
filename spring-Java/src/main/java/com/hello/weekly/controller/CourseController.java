package com.hello.weekly.controller;

import com.hello.weekly.Req.QueryCourse;
import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public ResponseData course(@RequestBody QueryCourse queryCourse)  {

        ResponsePage course = courseService.findCourse(queryCourse.getPageNum(),queryCourse.getPageSize(),queryCourse.getTechnicalDirection());

        //如果查询到，返回该类型，否则为null
        if (course != null) {

            return new ResponseData(ResponseData.success,"查找成功！",course);
        }
        return new ResponseData(ResponseData.warn,"该类型没有课程!");
    }

}
