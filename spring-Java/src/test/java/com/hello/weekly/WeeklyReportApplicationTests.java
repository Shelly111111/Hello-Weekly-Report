package com.hello.weekly;

import com.hello.weekly.mapper.UserMapper;
import com.hello.weekly.pojo.User;
import com.hello.weekly.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.SimpleQuery.list;
import static com.baomidou.mybatisplus.extension.toolkit.SimpleQuery.selectList;

@SpringBootTest
class WeeklyReportApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testFindUser() {
        User user = userService.findByUsername("admin");
        System.out.println(user.getPassword());
    }

    @Test
    public void testSelectList(){
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
