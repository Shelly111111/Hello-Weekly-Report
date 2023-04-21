package com.hello.weekly.controller;

import com.hello.weekly.Req.ChangeInfo;
import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.ChangePasswordService;
import com.hello.weekly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangePasswordController {

    @Autowired
    private UserService userService;
    @Autowired
    private ChangePasswordService changePasswordService;
    /***
     * 修改密码逻辑实现
     */
    @PostMapping("/change")
    public ResponseData change(@RequestBody ChangeInfo changeInfo ){

        User user = userService.findByUsername(changeInfo.getUsername());

        // 用户名不存在
        if (user == null) {
            return new ResponseData(ResponseData.notFound, "该用户不存在！");
        }
        //判断旧密码是否输入正确
        if(!user.getPassword().equals(changeInfo.getOldpassword())){
            return new ResponseData(ResponseData.error,"旧密码输入错误！");
        }
        //判断新旧密码是否一样
        if(user.getPassword().equals(changeInfo.getNewpassword())){
            return new ResponseData(ResponseData.warn, "新旧密码一样！");
        }
        //判断两次新密码是否一样
        if(!changeInfo.getNewpassword().equals(changeInfo.getNewpassword1())){
            return new ResponseData(ResponseData.warn,"两次新密码不一样！");
        }

            // 修改密码成功
            User a = new User(user.getId(), changeInfo.getUsername(), changeInfo.getNewpassword());
            changePasswordService.changePassword(a);
            return new ResponseData(ResponseData.success, "修改密码成功！");

    }
}
