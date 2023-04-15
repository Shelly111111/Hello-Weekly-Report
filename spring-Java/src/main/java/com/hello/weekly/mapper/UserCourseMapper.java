package com.hello.weekly.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hello.weekly.pojo.Course;
import com.hello.weekly.pojo.UserCourse;
import com.hello.weekly.pojo.UserCourse1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
@Mapper
//extends BaseMapper<UserCourse>
public interface UserCourseMapper extends BaseMapper<UserCourse>{


}
