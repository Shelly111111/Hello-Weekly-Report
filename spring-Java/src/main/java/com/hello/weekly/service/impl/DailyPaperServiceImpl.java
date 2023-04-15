package com.hello.weekly.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hello.weekly.Res.ResponseData;
import com.hello.weekly.Res.ResponsePage;
import com.hello.weekly.mapper.DailyPaperMapper;
import com.hello.weekly.pojo.DailyPaper;
import com.hello.weekly.service.DailyPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 日报表 服务实现类
 */

@Service
public class DailyPaperServiceImpl implements DailyPaperService {


    @Autowired
    private DailyPaperMapper dailyPaperMapper;

    @Override
    public ResponsePage getPaperByPage(int currentPage, int size) {
        Page<DailyPaper> page = new Page<>(currentPage, size);
        dailyPaperMapper.selectPage(page,null);
        return new ResponsePage(page.getTotal(),page.getRecords());
    }

    @Override
    public ResponseData addPaper(DailyPaper dailyPaper) {
        if (1 == dailyPaperMapper.insert(dailyPaper)){
            return new ResponseData(ResponseData.error, "添加成功！");
        }
        return new ResponseData(ResponseData.error, "添加失败！");
    }
}
