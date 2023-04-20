package com.hello.weekly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hello.weekly.pojo.UserDailyCheckin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDailyCheckinMapper extends BaseMapper<UserDailyCheckin> {
}
