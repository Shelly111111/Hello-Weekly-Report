package com.hello.weekly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hello.weekly.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @discription: 用户信息Mapper接口
 * @author: Zhang
 * @date: 2023/4/14
 */
@Repository
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
