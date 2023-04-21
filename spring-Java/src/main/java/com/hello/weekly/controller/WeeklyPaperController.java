package com.hello.weekly.controller;

import com.hello.weekly.Req.Paper.AddWeeklyPaper;
import com.hello.weekly.Req.Paper.GetPaper;
import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.pojo.User;
import com.hello.weekly.pojo.WeeklyPaper;
import com.hello.weekly.service.UserService;
import com.hello.weekly.service.WeeklyPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weeklypaper") // 路径跟菜单表的 url 字段一样
public class WeeklyPaperController {
    @Autowired
    private WeeklyPaperService weeklyPaperService;

    @Autowired
    private UserService userService;

    //获取周报（分页）
    @PostMapping
    public ResponsePage getPaper(@RequestBody GetPaper getPaper){
        User user = userService.findByUsername(getPaper.getUsername());

        return weeklyPaperService.getPaperByPage(getPaper.getCurrentpage(), getPaper.getSize(), user.getId());
    }

    //新增一条周报
    @PostMapping("/addpaper")
    public ResponseData addPaper(@RequestBody AddWeeklyPaper addWeeklyPaper) {
        User user = userService.findByUsername(addWeeklyPaper.getUsername());
        return weeklyPaperService.addPaper(addWeeklyPaper.getWeeklyPaper(), user.getId());
    }


    //根据日期获取周报
    @GetMapping("/getpaperbydate")
    public List<WeeklyPaper> getPaperByDate(String time, String username){
        User user = userService.findByUsername(username);
        return weeklyPaperService.getPaperByDate(time, user.getId());
    }
}
