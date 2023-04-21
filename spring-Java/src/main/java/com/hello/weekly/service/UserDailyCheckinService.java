package com.hello.weekly.service;

import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.pojo.UserDailyCheckin;

import java.util.Date;
import java.util.List;

/**
 * 打卡记录
 *
 * @author: 冯松
 * @updateauthor: 漫舞枪神
 * @updatedate: 2023/4/20
 */
public interface UserDailyCheckinService {
    // 根据用户ID查询打卡记录
    List<UserDailyCheckin> getAllByUserId(Integer user_id);

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
    ResponsePage getRecordByPage(Integer userId, Integer currentpage, Integer size);

    /**
     * 根据用户ID获取页面数量
     *
     * @param userId 用户Id
     * @param size   页面大小
     * @return 总页面数
     * @author: 漫舞枪神
     * @date: 2023/4/20
     */
    Long getPageTotalSize(Integer userId, Integer size);

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
    ResponsePage getRecordByDate(Integer userId, Date start_date, Date end_date, Integer currentpage, Integer size);

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
    Long getTotalWorkHour(Integer userId, Date start_date, Date end_date);
}
