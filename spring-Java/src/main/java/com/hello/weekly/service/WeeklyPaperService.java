package com.hello.weekly.service;

import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.pojo.DailyPaper;
import com.hello.weekly.pojo.WeeklyPaper;

import java.util.Date;
import java.util.List;

public interface WeeklyPaperService {
    ResponsePage getPaperByPage(int currentpage, int size, int userid);
    //查询所有日报
    ResponseData addPaper(WeeklyPaper weeklypaper, int userid);
    //新增一条日报
    List<WeeklyPaper> getPaperByDate(String time, int userid);
}
