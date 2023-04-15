package com.hello.weekly.controller;

import com.hello.weekly.pojo.UserCourse;
import com.hello.weekly.pojo.UserCourse1;
import com.hello.weekly.service.UserCourseService;
import com.hello.weekly.service.UserCourseService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserCourseController {

    @Autowired
    private UserCourseService1 userCourseService1;
    @Autowired
    private UserCourseService userCourseService;
    /***
     * 参与的课程
     */
    @PostMapping("/usercourse")
    public List<UserCourse1> userCourse(@RequestParam(value = "userid") Integer userid){//@RequestParam(value = "userid")

        List<UserCourse> userCourses = userCourseService.findUserCourse(userid);
        if(userCourses != null) {
            List<UserCourse1> userCourse1s = userCourseService1.selectAllUserCourse(userCourses);
            return userCourse1s;
        }


        return null;

    }
}
