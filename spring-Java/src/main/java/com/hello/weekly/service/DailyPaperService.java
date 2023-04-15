package com.hello.weekly.service;

import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.pojo.DailyPaper;

import java.util.List;

/**
 * 日报表 服务接口类
 *
 */
public interface DailyPaperService {
    ResponsePage getPaperByPage(int currentPage, int size);
    //查询所有日报
    Integer insertPaper(DailyPaper dailyPaper);
    //新增一条日报
}
