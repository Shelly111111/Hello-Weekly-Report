package com.hello.weekly.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.mapper.IdletimeMapper;
import com.hello.weekly.mapper.UserMapper;
import com.hello.weekly.pojo.IdleTime;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IdleTimeController {

    @Autowired
    private IdletimeMapper idletimeMapper;


    @Autowired
    private UserService userService;

    @GetMapping("/userdaily/idletime")
    public ResponseData findIdleTime(String username){

        if (userService.findByUsername(username)!=null){
        int userid  = userService.findByUsername(username).getId();
        IdleTime idleTime = idletimeMapper.selectById(userid);
        return new ResponseData(ResponseData.success,"查询成功！",idleTime);
        }else {
            return new ResponseData(ResponseData.notFound,"未找到用户");
        }
    }

    @PostMapping("/userdaily/idletime")
    public ResponseData update(IdleTime idleTime){

        idletimeMapper.updateById(idleTime);

        return  new ResponseData(ResponseData.success,"修改成功！");
    }

}
