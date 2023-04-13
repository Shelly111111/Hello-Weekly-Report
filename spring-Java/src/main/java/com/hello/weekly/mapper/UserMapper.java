package com.hello.weekly.mapper;

import com.hello.weekly.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    /**
     * 插入一条信息到数据库中
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * 通过用户名进行查询对应的用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}