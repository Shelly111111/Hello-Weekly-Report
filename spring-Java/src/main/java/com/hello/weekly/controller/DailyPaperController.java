package com.hello.weekly.controller;

import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.pojo.DailyPaper;
import com.hello.weekly.service.DailyPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 日报控制器
 */
@RestController
@RequestMapping("/dailypaper") // 路径跟菜单表的 url 字段一样
public class DailyPaperController {
    @Autowired
    private DailyPaperService dailyPaperService;

    //获取日报（分页）
    @PostMapping("/")
    public ResponsePage getPaper(@RequestParam(defaultValue =  "1") int currentpage,
                                 @RequestParam(defaultValue = "10") int size, String username){
        return dailyPaperService.getPaperByPage(currentpage, size, username);
    }


    //新增一条日报
    @PostMapping("/addpaper")
    public ResponseData addPaper(@RequestBody DailyPaper dailyPaper, String username) {
        return dailyPaperService.addPaper(dailyPaper, username);
    }

    //根据日期获取日报
    @GetMapping("/getpaperbydate")
    public List<DailyPaper> getPaperByDate(Date starttime, Date endtime, String username){
        return dailyPaperService.getPaperByDate(starttime, endtime, username);
    }
}
