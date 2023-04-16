package com.hello.weekly.controller;

import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.pojo.Course;
import com.hello.weekly.pojo.WeeklyPaper;
import com.hello.weekly.service.WeeklyPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeeklyPaperController {

    @Autowired
    private WeeklyPaperService weeklyPaperService;

    @PostMapping("/weeklypaper")
    public List<WeeklyPaper> weeklyPaper(@RequestParam(value = "userid") Integer userid){


            List<WeeklyPaper> weeklyPapers = weeklyPaperService.findWeeklyPaper(userid);
        if(weeklyPapers == null)
            return null;
        return weeklyPapers;
    }

    @PostMapping("/insertweeklypaper")
    public Boolean weeklyPaper1(@RequestParam(value = "id") Integer id,
                                @RequestParam(value = "userid")Integer userid,
                                @RequestParam(value = "date") String date,
                                @RequestParam(value = "completeWork") String completeWork,
                                @RequestParam(value = "nextPlan") String nextPlan,
                                @RequestParam(value = "delay") String delay){
        if(weeklyPaperService.inserWeeklyPaper(id,userid,date,completeWork,nextPlan,delay))
            return true;
        return false;

    }

}
