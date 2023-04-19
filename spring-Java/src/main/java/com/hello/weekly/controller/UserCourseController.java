package com.hello.weekly.controller;

import com.hello.weekly.Res.ResponseCourse;
import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.pojo.Course;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.CourseService;
import com.hello.weekly.service.UserCourseService;
import com.hello.weekly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserCourseController {

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;
    /***
     * 参与的课程
     */
    @PostMapping("/usercourse")
    public ResponseData userCourse(@RequestParam(value = "username") String username,
                                   @RequestParam(defaultValue =  "1") int pageNum,
                                   @RequestParam(defaultValue = "2") int pageSize){//@RequestParam(value = "userid")

        User user = userService.findByUsername(username);
        ResponseCourse responseCourse = userCourseService.findListCourse(pageNum,pageSize,user.getId());
        List<Course> courses = new ArrayList<>();
        for (int i = 0;i < responseCourse.getData().size();i++) {
            Course course = courseService.selectCourse(Integer.valueOf(responseCourse.getData().get(i).getCourseId()));
            courses.add(course);
        }
        ResponsePage responsePage = new ResponsePage(responseCourse.getTotal(),courses);
        if(responsePage == null)
            return new ResponseData(ResponseData.notFound,"课表为空！");
        return new ResponseData(ResponseData.success,"查找成功！",responsePage);

    }
}
