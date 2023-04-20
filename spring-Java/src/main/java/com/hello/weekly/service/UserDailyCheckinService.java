package com.hello.weekly.service;

import com.hello.weekly.pojo.UserDailyCheckin;

import java.util.List;

/**
 * 打卡记录
 *
 * @author: 冯松
 * @updateauthor: 漫舞枪神
 * @updatedate: 2023/4/20
 */
public interface UserDailyCheckinService {
    // 根据用户ID和日期查询打卡记录
    List<UserDailyCheckin> getAllByUserId(Integer user_id);

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
    List<UserDailyCheckin> getRecordByPage(Integer userId, Integer currentpage, Integer size);

    /**
     * 根据用户ID获取页面数量
     * @param userId
     * @param size
     * @return
     *
     * @author: 漫舞枪神
     * @date: 2023/4/20
     */
    Long getPageTotalSize(Integer userId, Integer size);
}
