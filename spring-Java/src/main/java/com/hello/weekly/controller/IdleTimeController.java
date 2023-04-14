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

    @GetMapping("/userdaily/idletime")
    public ResponseData findIdleTime(User user){

        //List<IdleTime> list = idletimeMapper.selectList(null);

        return new ResponseData(ResponseData.success,"查询成功！",idletimeMapper.selectById(user.getId()));
        //return new ResponseData(ResponseData.success,"",list);
    }

    @PostMapping("/userdaily/idletime")
    public ResponseData update(IdleTime idleTime){

        idletimeMapper.updateById(idleTime);

        return  new ResponseData(ResponseData.success,"查询成功！");
    }

}
