package com.hello.weekly.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.mapper.IdletimeMapper;
import com.hello.weekly.mapper.UserMapper;
import com.hello.weekly.pojo.IdleTime;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.IdletimeService;
import com.hello.weekly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
public class IdleTimeController {

    @Autowired
    private IdletimeMapper idletimeMapper;

    @Autowired
    private IdletimeService idletimeService;

    @Autowired
    private UserService userService;
    //http://localhost:8088/userdaily/idletime?username=admin
    @GetMapping("/userdaily/idletime")
    public ResponseData findIdleTime(@RequestParam(value = "username") String username){

        if (userService.findByUsername(username)!=null) {

            int userid = userService.findByUsername(username).getId();

            QueryWrapper<IdleTime> wrapper = new QueryWrapper<IdleTime>();
            wrapper.eq("userid", userid);
            List<IdleTime> list = idletimeMapper.selectList(wrapper);
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = null;
            try {
                jsonStr = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(jsonStr);
            return new ResponseData(ResponseData.success, "查询成功！",list);

        }else {
            return new ResponseData(ResponseData.notFound,"未找到用户");
        }
    }

    @PostMapping("/userdaily/idletime")
    public ResponseData update(@RequestBody  List<IdleTime> idleTimeList){
        Boolean iS =idletimeService.saveOrUpdateBatch(idleTimeList);
        //int num = idletimeMapper.updateById(idleTime);
        if (iS) {
            return new ResponseData(ResponseData.success, "修改成功！");
        } else {
            return new ResponseData(ResponseData.error, "修改失败");
        }
    }

}

