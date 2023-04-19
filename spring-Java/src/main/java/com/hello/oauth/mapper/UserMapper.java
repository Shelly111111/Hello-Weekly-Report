package com.hello.oauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hello.oauth.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}