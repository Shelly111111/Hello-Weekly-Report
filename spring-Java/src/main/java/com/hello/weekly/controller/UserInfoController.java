package com.hello.weekly.controller;

import com.hello.weekly.Req.UpdateUserInfo;
import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.pojo.User;
import com.hello.weekly.pojo.UserInfo;
import com.hello.weekly.service.UserInfoService;
import com.hello.weekly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if (userInfo == null) {
            return new ResponseData(ResponseData.error, "查找失败！");
        }

        //查找成功
        return new ResponseData(ResponseData.success, "查找成功！", userInfo);
    }


    /***
     * 修改用户信息逻辑实现
     * @param updateUserInfo com.hello.weekly.Req.UpdateUserInfo
     * @return com.hello.weekly.Res.ResponseData
     *
     * @author: Zhang
     * @date: 2023/4/14
     * @updatedate: 2023/4/20
     */
    @PostMapping("/userInfo")
    public ResponseData updateUserInfo(@RequestBody UpdateUserInfo updateUserInfo) {

        User user = userService.findByUsername(updateUserInfo.getUsername());

        // 用户名不存在
        if (user == null) {
            return new ResponseData(ResponseData.notFound, "该用户不存在！");
        }

        UserInfo oldUserInfo = userInfoService.findByUid(user.getId());
        if (oldUserInfo == null) {
            return new ResponseData(ResponseData.error, "更新用户信息失败！");
        }

        UserInfo userInfo = new UserInfo(oldUserInfo.getId(), updateUserInfo.getNickName(), updateUserInfo.getHeadSculpture(),
                updateUserInfo.getCollege(), updateUserInfo.getMajor(), updateUserInfo.getGrade(), user.getId());
        // 更新用户信息失败
        if (userInfoService.updateUserInfo(userInfo) == 0) {
            return new ResponseData(ResponseData.error, "更新用户信息失败！");
        }

        //更新成功
        return new ResponseData(ResponseData.success, "更新用户信息成功！");
    }
}
