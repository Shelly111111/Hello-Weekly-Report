package com.hello.weekly.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@TableName("ClockInRecord")
public class UserDailyCheckin {
    private Integer Id;

    @TableField(value = "userId")
    private Integer user_id;

    private Date date;

    private String time;

    @TableField(value = "workHour")
    private Integer work_hour;


}
