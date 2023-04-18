package com.hello.weekly.controller;

import com.hello.weekly.pojo.UserDailyCheckin;
import com.hello.weekly.service.UserDailyCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userdailycheckin")
public class UserDailyCheckinController {
    @Autowired
    private UserDailyCheckinService userDailyCheckinService;

    @GetMapping
    public ResponseEntity<List<UserDailyCheckin>> getByUserIdAndDate(int userId) {
        List<UserDailyCheckin> userDailyCheckinList = userDailyCheckinService.getAllByUserId(userId);
        return new ResponseEntity<>(userDailyCheckinList, HttpStatus.OK);
    }
}
