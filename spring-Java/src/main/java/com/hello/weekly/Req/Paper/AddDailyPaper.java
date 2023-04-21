package com.hello.weekly.Req.Paper;

import com.hello.weekly.pojo.DailyPaper;
import lombok.*;

/**
 * 增加日报接口接收数据
 *
 * @author: 漫舞枪神
 * @date: 2023/4/21
 */
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class AddDailyPaper {
    private DailyPaper dailyPaper;
    private String username;
}
