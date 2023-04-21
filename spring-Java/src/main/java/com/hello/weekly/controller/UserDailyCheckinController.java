package com.hello.weekly.controller;

import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.pojo.User;
import com.hello.weekly.pojo.UserDailyCheckin;
import com.hello.weekly.service.UserDailyCheckinService;
import com.hello.weekly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 上月打卡记录
 *
 * @author: 冯松
 * @updateauthor: 漫舞枪神
 * @updatedate: 2023/4/20
 */
@RestController
@RequestMapping("/userdailycheckin")
public class UserDailyCheckinController {
    @Autowired
    private UserDailyCheckinService userDailyCheckinService;
    @Autowired
    private UserService userService;

    /**
     * @param username    用户名
     * @param currentPage 当前页
     * @param size        每页数量
     * @return
     * @author: 冯松
     * @updateauthor: 漫舞枪神
     * @updatedate: 2023/4/20
     */
    @GetMapping
    public ResponseData getByUserIdAndDate(@RequestParam("username") String username,
                                           @RequestParam("currentPage") Integer currentPage,
                                           @RequestParam("size") Integer size) {
        User user = userService.findByUsername(username);
        // 用户名不存在
        if (user == null) {
            return new ResponseData(ResponseData.notFound, "该用户不存在！");
        }
        ResponsePage responsePage = userDailyCheckinService.getRecordByPage(user.getId(), currentPage, size);
        return new ResponseData(ResponseData.success, "查找成功", responsePage);
    }
}
