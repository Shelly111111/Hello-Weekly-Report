package com.hello.weekly.service.impl;

import com.hello.weekly.mapper.UserCourseMapper;
import com.hello.weekly.mapper.WeeklyPaperMapper;
import com.hello.weekly.pojo.UserCourse;
import com.hello.weekly.pojo.WeeklyPaper;
import com.hello.weekly.service.WeeklyPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeeklyPaperServiceImpl implements WeeklyPaperService {

    @Autowired
    private WeeklyPaperMapper weeklyPaperMapper;
    @Autowired
    private UserCourseMapper userCourseMapper;

    @Override
    public List<WeeklyPaper> findWeeklyPaper(Integer userid){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userid);
        List<WeeklyPaper> weeklyPapers = weeklyPaperMapper.selectByMap(map);
        if(weeklyPapers == null)
            return null;
        return weeklyPapers;
    }

    @Override
    public Boolean inserWeeklyPaper(Integer id,Integer userid,String date,String completeWork,String nextPlan,String delay){
        WeeklyPaper weeklyPaper = new WeeklyPaper(id,userid,date,completeWork,nextPlan,delay);
        Integer a = weeklyPaperMapper.insert(weeklyPaper);
        if(a == 1)
            return true;
        return false;
    }


}
