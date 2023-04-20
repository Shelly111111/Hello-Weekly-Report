package com.hello.weekly.controller;

import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.DailyPaperService;
import com.hello.weekly.service.UserDailyCheckinService;
import com.hello.weekly.service.UserService;
import com.hello.weekly.service.WeeklyPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.hello.weekly.tools.Utils.getCurrentWeekTimeFrame;

/**
 * 我的统计信息
 *
 * @author: 漫舞枪神
 * @date: 2023/4/20
 */
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private UserDailyCheckinService userDailyCheckinService;
    @Autowired
    private UserService userService;
    @Autowired
    private DailyPaperService dailyPaperService;
    @Autowired
    private WeeklyPaperService weeklyPaperService;

    /**
     * 我的统计信息
     *
     * @param username 用户名
     * @return 统计信息
     */
    @GetMapping
    public ResponseData statistic(@RequestParam("username") String username) {
        User user = userService.findByUsername(username);
        Map<String, Object> map = new HashMap<>();

        Date[] times = getCurrentWeekTimeFrame();
        Long totalWorkHour = userDailyCheckinService.getTotalWorkHour(user.getId(), times[0], times[1]);
        map.put("totalWeeklyWorkHour", totalWorkHour);

        Integer totalDailyPaper = dailyPaperService.getTotalCount(user.getId());
        map.put("totalDailyPaper", totalDailyPaper);

        Integer totalWeeklyPaper = weeklyPaperService.getTotalCount(user.getId());
        map.put("totalWeeklyPaper", totalWeeklyPaper);

        return new ResponseData(ResponseData.success, "查询成功", map);
    }
}
