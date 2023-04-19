package com.hello.weekly.controller;

import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public ResponseData course(@RequestParam(value = "technicalDirection") String technicalDirection,
                               @RequestParam(defaultValue =  "1") int pageNum,
                               @RequestParam(defaultValue = "2") int pageSize)  {

        ResponsePage course = courseService.findCourse(pageNum,pageSize,technicalDirection);

        //如果查询到，返回该类型，否则为null
        if (course != null) {

            return new ResponseData(ResponseData.success,"查找成功！",course);
        }
        return new ResponseData(ResponseData.warn,"该类型没有课程!");
    }

}
