package com.hello.weekly.Req.Paper;

import com.hello.weekly.pojo.WeeklyPaper;
import lombok.*;

/**
 * 增加周报接口接收数据
 *
 * @author: 漫舞枪神
 * @date: 2023/4/21
 */
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class AddWeeklyPaper {
    private WeeklyPaper weeklyPaper;
    private String username;
}
