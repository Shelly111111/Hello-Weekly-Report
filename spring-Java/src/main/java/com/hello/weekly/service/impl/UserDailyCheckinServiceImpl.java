package com.hello.weekly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.mapper.UserDailyCheckinMapper;
import com.hello.weekly.pojo.UserDailyCheckin;
import com.hello.weekly.service.UserDailyCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 打卡记录
 *
 * @author: 冯松
 * @updateauthor: 漫舞枪神
 * @updatedate: 2023/4/20
 */
@Service
public class UserDailyCheckinServiceImpl implements UserDailyCheckinService {
    @Autowired
    private UserDailyCheckinMapper userDailyCheckinMapper;

    /**
     * @param user_id 用户Id
     * @return 打卡记录列表
     * @author: 冯松
     * @updateauthor: 漫舞枪神
     * @updatedate: 2023/4/20
     */
    @Override
    public List<UserDailyCheckin> getAllByUserId(Integer user_id) {
        LambdaQueryWrapper<UserDailyCheckin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDailyCheckin::getUser_id, user_id);
        return userDailyCheckinMapper.selectList(wrapper);
    }

    /**
     * 根据用户ID获取分页数据
     *
     * @param userId      用户Id
     * @param currentpage 当前页码
     * @param size        页面大小
     * @return 打卡记录列表
     * @author: 漫舞枪神
     * @date: 2023/4/20
     */
    @Override
    public ResponsePage getRecordByPage(Integer userId, Integer currentpage, Integer size) {
        LambdaQueryWrapper<UserDailyCheckin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDailyCheckin::getUser_id, userId);
        double count = userDailyCheckinMapper.selectCount(wrapper);
        Page<UserDailyCheckin> page = new Page<>(currentpage, size);
        return new ResponsePage((long) Math.ceil(count / size), userDailyCheckinMapper.selectPage(page, wrapper).getRecords());
    }

    /**
     * 根据用户ID获取总页面数
     *
     * @param userId 用户Id
     * @param size   页面大小
     * @return 总页面数 Long
     * @author: 漫舞枪神
     * @date: 2023/4/20
     */
    @Override
    public Long getPageTotalSize(Integer userId, Integer size) {
        LambdaQueryWrapper<UserDailyCheckin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDailyCheckin::getUser_id, userId);
        double count = userDailyCheckinMapper.selectCount(wrapper);
        return (long) Math.ceil(count / size);
    }

    /**
     * 查询用户从开始日期到结束日期的打卡记录
     *
     * @param userId      用户Id
     * @param start_date  开始日期
     * @param end_date    结束日期
     * @param currentpage 当前页码
     * @param size        页面大小
     * @return 打卡记录列表
     * @author: 漫舞枪神
     * @date: 2023/4/20
     */
    @Override
    public ResponsePage getRecordByDate(Integer userId, Date start_date, Date end_date, Integer currentpage, Integer size) {
        LambdaQueryWrapper<UserDailyCheckin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDailyCheckin::getUser_id, userId).ge(UserDailyCheckin::getDate, start_date).lt(UserDailyCheckin::getDate, end_date);
        double count = userDailyCheckinMapper.selectCount(wrapper);
        Page<UserDailyCheckin> page = new Page<>(currentpage, size);
        return new ResponsePage((long) Math.ceil(count / size), userDailyCheckinMapper.selectPage(page, wrapper).getRecords());
    }

    /**
     * 查询用户从开始日期到结束日期的总工时
     *
     * @param userId     用户Id
     * @param start_date 开始日期
     * @param end_date   结束日期
     * @return 总工时
     * @author: 漫舞枪神
     * @date: 2023/4/20
     */
    @Override
    public Long getTotalWorkHour(Integer userId, Date start_date, Date end_date) {
        LambdaQueryWrapper<UserDailyCheckin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDailyCheckin::getUser_id, userId).ge(UserDailyCheckin::getDate, start_date).lt(UserDailyCheckin::getDate, end_date);
        List<UserDailyCheckin> userDailyCheckins = userDailyCheckinMapper.selectList(wrapper);
        Long totalWorkHour = Long.valueOf(0);
        for (UserDailyCheckin udc : userDailyCheckins) {
            totalWorkHour += udc.getWork_hour();
        }
        return totalWorkHour;
    }
}