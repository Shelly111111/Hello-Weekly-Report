package com.hello.weekly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.mapper.DailyPaperMapper;
import com.hello.weekly.mapper.UserMapper;
import com.hello.weekly.pojo.DailyPaper;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.DailyPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 日报表 服务实现类
 */

@Service
public class DailyPaperServiceImpl implements DailyPaperService {


    @Autowired
    private DailyPaperMapper dailyPaperMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponsePage getPaperByPage(int currentpage, int size, int userid) {
        //根据username查询用户表中用户id

        QueryWrapper<DailyPaper> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", userid);
        Page<DailyPaper> page = new Page<>(currentpage, size);
        dailyPaperMapper.selectPage(page, wrapper);
        return new ResponsePage(page.getTotal(), page.getRecords());
    }

    @Override
    public ResponseData addPaper(DailyPaper dailyPaper, int userid) {


        dailyPaper.setUserId(userid);

        if (1 == dailyPaperMapper.insert(dailyPaper)) {
            return new ResponseData(ResponseData.error, "添加成功！");
        }
        return new ResponseData(ResponseData.error, "添加失败！");
    }

    @Override
    public List<DailyPaper> getPaperByDate(Date starttime, Date endtime, int userid) {

        try {
            QueryWrapper<DailyPaper> wrapper2 = new QueryWrapper<>();
            wrapper2.between("date", starttime, endtime).eq("userid", userid).orderByAsc("date");
            //条件构造器。等于'date' = '2023-04-17'

            //根据用户id和日期查询日报

            return dailyPaperMapper.selectList(wrapper2);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}
