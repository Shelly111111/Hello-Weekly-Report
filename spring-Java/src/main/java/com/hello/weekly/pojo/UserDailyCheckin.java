package com.hello.weekly.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@TableName("ClockInRecord")
public class UserDailyCheckin {

    @JsonIgnore
    @JSONField(serialize = false)
    private Integer Id;

    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(value = "userId")
    private Integer user_id;

    private Date date;

    private Time time;

    @TableField(value = "workHour")
    private Integer work_hour;


}
