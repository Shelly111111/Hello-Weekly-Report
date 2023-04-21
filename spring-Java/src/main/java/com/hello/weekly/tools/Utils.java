package com.hello.weekly.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 工具类
 *
 * @author: 漫舞枪神
 * @date: 2023/4/20
 */
public class Utils {

    /**
     * 获取本周的开始时间和结束时间
     *
     * @return 本周的开始时间和结束时间
     * @author: 漫舞枪神
     * @date: 2023/4/20
     */
    public static Date[] getCurrentWeekTimeFrame() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //start of the week
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        calendar.add(Calendar.DAY_OF_WEEK, -(calendar.get(Calendar.DAY_OF_WEEK) - 2));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date startTime = calendar.getTime();
        //end of the week
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endTime = calendar.getTime();
        return new Date[]{startTime, endTime};
    }
}
