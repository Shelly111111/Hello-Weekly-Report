package com.hello.weekly.service;

import com.hello.weekly.pojo.WeeklyPaper;

import java.util.List;

public interface WeeklyPaperService {

    /***
     * 寻找对应userid的周报
     * @param userid
     * @return
     */
     List<WeeklyPaper> findWeeklyPaper(Integer userid);

    /***
     * 录入周报
     * @param userid
     * @param date
     * @param completeWork
     * @param nextPlan
     * @param delay
     * @return
     */
     Boolean inserWeeklyPaper(Integer id,Integer userid,String date,String completeWork,String nextPlan,String delay);
}
