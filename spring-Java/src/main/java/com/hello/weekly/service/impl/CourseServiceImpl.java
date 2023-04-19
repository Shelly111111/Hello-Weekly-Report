package com.hello.weekly.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.mapper.CourseMapper;
import com.hello.weekly.pojo.Course;
import com.hello.weekly.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;


    /***
     * 从数据库中选择信息,并实现分页
     */
    @Override
    public ResponsePage findCourse(Integer pageNum,Integer pageSize,String technicalDirection){
        Page<Course> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Course> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(Course::getTechnicalDirection , technicalDirection);
        courseMapper.selectPage(page,userLambdaQueryWrapper);
        return new ResponsePage(page.getTotal(),page.getRecords());
    }

    /***
     * 根据userid
     * @param userid
     * @return
     */

    @Override
    public Course selectCourse(Integer userid){
         Course courses = courseMapper.selectById(userid);
        return courses;
    };

}
