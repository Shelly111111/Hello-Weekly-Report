package com.hello.weekly.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hello.weekly.mapper.IdletimeMapper;
import com.hello.weekly.pojo.IdleTime;
import com.hello.weekly.service.IdletimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
@Service
public class IdletimeServiceImpl extends ServiceImpl<IdletimeMapper,IdleTime> implements IdletimeService{


}
