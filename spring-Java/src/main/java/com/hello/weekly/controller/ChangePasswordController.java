package com.hello.weekly.controller;

import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.ChangePasswordService;
import com.hello.weekly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseData change(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password){

        User user = userService.findByUsername(username);

        // 用户名不存在
        if (user == null) {
            return new ResponseData(ResponseData.notFound, "该用户不存在！");
        }


        if(user.getPassword().equals(password)){
            return new ResponseData(ResponseData.warn, "新旧密码一样！");
        }

            // 修改密码成功
            User a = new User(user.getId(), username, password);
            if (changePasswordService.changePassword(a) == null)
                return new ResponseData(ResponseData.error, "修改密码失败！");
            return new ResponseData(ResponseData.success, "修改密码成功！");

    }
}
