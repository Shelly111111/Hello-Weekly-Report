package com.hello.weekly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hello.weekly.mapper.UserDailyCheckinMapper;
import com.hello.weekly.pojo.UserDailyCheckin;
import com.hello.weekly.service.UserDailyCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     *
     * @param user_id
     * @return
     *
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
     * @param userId
     * @param currentpage
     * @param size
     * @return
     *
     * @author: 漫舞枪神
     * @date: 2023/4/20
     */
    @Override
    public List<UserDailyCheckin> getRecordByPage(Integer userId, Integer currentpage, Integer size) {
        LambdaQueryWrapper<UserDailyCheckin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDailyCheckin::getUser_id, userId);
        Page<UserDailyCheckin> page = new Page<>(currentpage, size);
        return userDailyCheckinMapper.selectPage(page,wrapper).getRecords();
    }

    /**
     * 根据用户ID获取页面数量
     * @param userId
     * @param size
     * @return
     *
     * @author: 漫舞枪神
     * @date: 2023/4/20
     */
    @Override
    public Long getPageTotalSize(Integer userId, Integer size) {
        LambdaQueryWrapper<UserDailyCheckin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDailyCheckin::getUser_id, userId);
        double count = userDailyCheckinMapper.selectCount(wrapper);
        return (long) Math.ceil(count/size);
    }
}