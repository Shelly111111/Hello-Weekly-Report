package com.hello.weekly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hello.weekly.pojo.IdleTime;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IdletimeMapper extends BaseMapper<IdleTime> {

}
