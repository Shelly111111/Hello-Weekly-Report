package com.hello.weekly.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.mapper.DailyPaperMapper;
import com.hello.weekly.mapper.UserMapper;
import com.hello.weekly.mapper.WeeklyPaperMapper;
import com.hello.weekly.pojo.DailyPaper;
import com.hello.weekly.pojo.User;
import com.hello.weekly.pojo.WeeklyPaper;
import com.hello.weekly.service.WeeklyPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 周报服务实现类
 */
@Service
public class WeeklyPaperServiceImpl implements WeeklyPaperService {


    @Autowired
    private WeeklyPaperMapper weeklyPaperMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     *
     * @param currentpage
     * @param size
     * @param userid
     * @return
     */
    @Override
    public ResponsePage getPaperByPage(int currentpage, int size, int userid) {


        QueryWrapper<WeeklyPaper> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", userid);
        Page<WeeklyPaper> page = new Page<>(currentpage, size);
        weeklyPaperMapper.selectPage(page, wrapper);
        return new ResponsePage(page.getTotal(), page.getRecords());
    }

    /**
     *
     * @param weeklyPaper
     * @param userid
     * @return
     */
    @Override
    public ResponseData addPaper(WeeklyPaper weeklyPaper, int userid) {

        weeklyPaper.setUserId(userid);

        if (1 == weeklyPaperMapper.insert(weeklyPaper)) {
            return new ResponseData(ResponseData.error, "添加成功！");
        }
        return new ResponseData(ResponseData.error, "添加失败！");

    }

    /**
     *
     * @param time
     * @param userid
     * @return
     */
    @Override
    public List<WeeklyPaper> getPaperByDate(String time, int userid) {

        try {
            QueryWrapper<WeeklyPaper> wrapper = new QueryWrapper<>();
            wrapper.eq("date", time).eq("userid", userid).orderByAsc("date");
            //根据用户id和日期查询日报
            return weeklyPaperMapper.selectList(wrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
