package com.hello.weekly.controller;

import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * @discription: 登录逻辑的实现
     *
     * @param username String
     * @param password String
     * @return
     *
     * @author: Zhang
     * @date: 2023/4/13
     */
    @PostMapping("/login")
    public ResponseData login(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password) {
        // 用于存储相关的数据信息
        Map<String, Object> map = new HashMap<>();

        User user = userService.findByUsername(username);

        // 用户名不存在
        if (user == null) {
            return new ResponseData(ResponseData.notFound, "该用户不存在！");
        }

        // 用户输入的密码错误
        if (!user.getPassword().equals(password)) {
            return new ResponseData(ResponseData.error, "你所输入的密码错误！");
        }

        // 登录成功
        return new ResponseData(ResponseData.success, "登陆成功！");
    }
}
