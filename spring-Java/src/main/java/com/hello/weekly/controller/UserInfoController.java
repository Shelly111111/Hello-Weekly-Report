package com.hello.weekly.controller;

import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.pojo.User;
import com.hello.weekly.pojo.UserInfo;
import com.hello.weekly.service.UserInfoService;
import com.hello.weekly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Year;

/**
 * @discription: 用户信息管理
 * @author: Zhang
 * @date: 2023/4/14
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    /***
     * 查找用户信息逻辑实现
     * @param username String 用户名
     * @return ResponseData
     *
     * @author: Zhang
     * @date: 2023/4/14
     */
    @GetMapping("/userInfo")
    public ResponseData userInfo(@RequestParam(value = "username") String username) {
        User user = userService.findByUsername(username);

        // 用户名不存在
        if (user == null) {
            return new ResponseData(ResponseData.notFound, "该用户不存在！");
        }

        //查找失败
        UserInfo userInfo = userInfoService.findByUid(user.getId());
        if ( userInfo == null) {
            return new ResponseData(ResponseData.error, "查找失败！");
        }

        //查找成功
        return new ResponseData(ResponseData.success, "查找成功！", userInfo);
    }


    /***
     * 修改用户信息逻辑实现
     * @param username String 用户名
     * @param nickName String 昵称
     * @param headSculpture String 头像 base64
     * @param college String 学院
     * @param major String 专业
     * @param grade Integer 年级
     * @return ResponseData
     *
     * @author: Zhang
     * @date: 2023/4/14
     */
    @PostMapping("/userInfo")
    public ResponseData updateUserInfo(@RequestParam(value = "username") String username,
                                 @RequestParam(value = "nickName") String nickName,
                                 @RequestParam(value = "headSculpture") String headSculpture,
                                 @RequestParam(value = "college") String college,
                                 @RequestParam(value = "major") String major,
                                 @RequestParam(value = "grade") Integer grade) {

        User user = userService.findByUsername(username);

        // 用户名不存在
        if (user == null) {
            return new ResponseData(ResponseData.notFound, "该用户不存在！");
        }

        UserInfo userInfo = new UserInfo(user.getId(), nickName, headSculpture, college, major, grade);
        if (userInfoService.findByUid(userInfo.getId()) == null) {
            return new ResponseData(ResponseData.error, "更新用户信息失败！");
        }

        // 更新用户信息失败
        if (userInfoService.updateUserInfo(userInfo) == 0) {
            return new ResponseData(ResponseData.error, "更新用户信息失败！");
        }

        //更新成功
        return new ResponseData(ResponseData.success, "更新用户信息成功！");
    }
}
