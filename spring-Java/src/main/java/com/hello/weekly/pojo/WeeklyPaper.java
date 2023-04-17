package com.hello.weekly.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@TableName("WeeklyPaper")
public class WeeklyPaper {

    @TableField("Id")
    private Integer Id;

    @TableField("userId")
    private Integer userId;

    @TableField("date")
    private String date;

    @TableField("completeWork")
    private String completeWork;

    @TableField("nextPlan")
    private String nextPlan;

    @TableField("delay")
    private String delay;
}
