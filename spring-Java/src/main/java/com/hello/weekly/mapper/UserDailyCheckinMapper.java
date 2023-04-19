package com.hello.weekly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hello.weekly.pojo.UserDailyCheckin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDailyCheckinMapper extends BaseMapper<UserDailyCheckin> {
    @Select("select * from clockinrecord where userId = #{user_id}")
    public List<UserDailyCheckin> getAllByUserId(Integer user_id);
}
