package com.hello.weekly;

import com.hello.weekly.pojo.User;
import com.hello.weekly.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeeklyReportApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testFindUser() {
        User user = userService.findByUsername("admin");
        System.out.println(user.getPassword());
    }

}
