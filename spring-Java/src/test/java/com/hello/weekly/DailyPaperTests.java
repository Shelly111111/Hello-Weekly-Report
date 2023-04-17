package com.hello.weekly;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hello.weekly.mapper.DailyPaperMapper;
import com.hello.weekly.pojo.DailyPaper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class DailyPaperTests {

    @Autowired
    private DailyPaperMapper dailyPaperMapper;

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
}

