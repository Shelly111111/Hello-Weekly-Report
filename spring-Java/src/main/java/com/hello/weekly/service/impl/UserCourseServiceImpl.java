package com.hello.weekly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hello.weekly.Res.ResponseCourse;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.mapper.CourseMapper;
import com.hello.weekly.mapper.UserCourseMapper;
import com.hello.weekly.pojo.Course;
import com.hello.weekly.pojo.UserCourse;
import com.hello.weekly.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    private UserCourseMapper userCourseMapper;
    @Autowired
    private CourseMapper courseMapper;


    /***
     * 查找对应userid用户课程
     * @param userid
     * @return
     */
    @Override
    public List<UserCourse> findUserCourse(Integer userid){

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userid);
        List<UserCourse> userCourses = userCourseMapper.selectByMap(map);

        if(userCourses == null)
            return null;
        return userCourses;
    }

    /***
     * 查找用户参与的课程
     * @param userCourses
     * @return
     */
    @Override
    public List<Course> findCourse(List<UserCourse> userCourses){

        List<Course> userCourse1s = new ArrayList<>();
        for (UserCourse userCourse : userCourses
        ) {
            Course course = courseMapper.selectById(userCourse.getCourseId());
            Course a = new Course(course.getId(), course.getTitle(), course.getDescription(), course.getDateTime(), course.getTechnicalDirection(), course.getLevel(), course.getMode());
            userCourse1s.add(a);
        }

        return userCourse1s;
    }


    /***
     * 根据uesrid查取并分页
     * @param pageNum
     * @param pageSize
     * @param userid
     * @return
     */
   @Override
    public ResponseCourse findListCourse(Integer pageNum, Integer pageSize, Integer userid){

       Page<UserCourse> page = new Page<>(pageNum,pageSize);
       LambdaQueryWrapper<UserCourse> userLambdaQueryWrapper = Wrappers.lambdaQuery();
       userLambdaQueryWrapper.like(UserCourse::getUserId , userid);
       userCourseMapper.selectPage(page,userLambdaQueryWrapper);
       return new ResponseCourse(page.getTotal(),page.getRecords());

   }
}
