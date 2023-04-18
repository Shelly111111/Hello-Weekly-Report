package com.hello.weekly.service;

import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.pojo.DailyPaper;

import java.util.Date;
import java.util.List;

/**
 * 日报表 服务接口类
 *
 */
public interface DailyPaperService {
    ResponsePage getPaperByPage(int currentPage, int size, String username);
    //查询所有日报
    ResponseData addPaper(DailyPaper dailyPaper, String username);
    //新增一条日报
    List<DailyPaper> getPaperByDate(Date starttime, Date endtime, String username);
}
