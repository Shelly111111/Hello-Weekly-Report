package com.hello.weekly;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hello.weekly.mapper.DailyPaperMapper;
import com.hello.weekly.mapper.UserMapper;
import com.hello.weekly.pojo.DailyPaper;
import com.hello.weekly.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class DailyPaperTests {

    @Autowired
    private DailyPaperMapper dailyPaperMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetByPage(){
        Page<DailyPaper> page = new Page<>(2,2);
        //当前页码和每页显示条数
        dailyPaperMapper.selectPage(page,null);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());

    }

    @Test
    public void testInsert(){
        Date date = new Date();
        System.out.println(date);
        DailyPaper dailyPaper = new DailyPaper();
        dailyPaper.setUserId(2);
        dailyPaper.setRisk("添加失败");
        dailyPaper.setCompleteWork("添加日报");
        dailyPaper.setDelay(true);
        dailyPaper.setDate(new Date());
        int result = dailyPaperMapper.insert(dailyPaper);
        System.out.println(result);
    }

    @Test
    public void testGetByDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date date = format.parse("2023-04-15",pos);

        String username = "admin";
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);

        List<User> users = userMapper.selectList(wrapper);
        Integer userid = users.get(0).getId();

        QueryWrapper<DailyPaper> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("date",date).eq("userid",userid);
        //条件构造器。等于'date' = '2023-04-17'
        dailyPaperMapper.selectList(wrapper2);


//        dailyPaperMapper.getDailyPaperByDate(date,username);


    }
}

