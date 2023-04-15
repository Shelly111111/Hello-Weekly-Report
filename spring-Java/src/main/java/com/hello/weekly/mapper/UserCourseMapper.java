package com.hello.weekly.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hello.weekly.pojo.UserCourse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;



@Repository
@Mapper
//extends BaseMapper<UserCourse>
public interface UserCourseMapper extends BaseMapper<UserCourse>{


}
