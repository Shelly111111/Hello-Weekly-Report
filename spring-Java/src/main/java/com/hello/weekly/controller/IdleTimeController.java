package com.hello.weekly.controller;

import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.mapper.IdletimeMapper;
import com.hello.weekly.pojo.IdleTime;
import com.hello.weekly.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IdleTimeController {

    @Autowired
    private IdletimeMapper idletimeMapper;

    @GetMapping("/user/idletime")
    public ResponseData findIdleTime(User user){

        //List<IdleTime> list = idletimeMapper.selectList(null);

        return new ResponseData(ResponseData.success,"",idletimeMapper.selectById(user.getId()));
        //return new ResponseData(ResponseData.success,"",list);
    }

    @PostMapping("/user/update")
    public ResponseData update(IdleTime idleTime){

        idletimeMapper.updateById(idleTime);

        return  new ResponseData(ResponseData.success,"");
    }

}
