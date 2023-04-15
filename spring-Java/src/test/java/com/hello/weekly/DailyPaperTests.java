package com.hello.weekly;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hello.weekly.mapper.DailyPaperMapper;
import com.hello.weekly.pojo.DailyPaper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DailyPaperTests {

    @Autowired
    private DailyPaperMapper dailyPaperMapper;

    @Test
    public void selectByPage(){
        Page<DailyPaper> page = new Page<>(2,2);
        //当前页码和每页显示条数
        dailyPaperMapper.selectPage(page,null);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
//        QueryWrapper<UserBean> wrapper = new QueryWrapper<>();
//        wrapper.isNotNull("user_id");

//         创建分页对象（1表示第一页；4表示每页大小为4）
//        Page<UserBean> page = new Page<>(1, 4);
//        Page<UserBean> result = simpleMapper.selectPage(page, wrapper);
//        System.out.println("page == result: " + (page == result));
//        System.out.println("size: " + result.getSize());
//        System.out.println("total: " + result.getTotal());
//        for(UserBean userBean : result.getRecords()) {
//            System.out.println(userBean);
    }
}

