package com.hello.weekly.controller;

import com.hello.weekly.Req.Paper.AddDailyPaper;
import com.hello.weekly.Req.Paper.GetPaper;
import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.pojo.DailyPaper;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.DailyPaperService;
import com.hello.weekly.service.UserService;
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

    @Autowired
    private UserService userService;

    //获取日报（分页）
    @PostMapping
    public ResponsePage getPaper(@RequestBody GetPaper getPaper){
        User user = userService.findByUsername(getPaper.getUsername());

        return dailyPaperService.getPaperByPage(getPaper.getCurrentpage(), getPaper.getSize(), user.getId());
    }


    //新增一条日报
    @PostMapping("/addpaper")
    public ResponseData addPaper(@RequestBody AddDailyPaper addDailyPaper) {
        User user = userService.findByUsername(addDailyPaper.getUsername());
        return dailyPaperService.addPaper(addDailyPaper.getDailyPaper(), user.getId());
    }

    //根据日期获取日报
    @GetMapping("/getpaperbydate")
    public List<DailyPaper> getPaperByDate(Date starttime, Date endtime, String username){
        User user = userService.findByUsername(username);
        return dailyPaperService.getPaperByDate(starttime, endtime, user.getId());
    }
}
