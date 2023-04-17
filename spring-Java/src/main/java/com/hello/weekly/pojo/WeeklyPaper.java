package com.hello.weekly.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
<<<<<<< HEAD
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
    private  Integer userId;

    @TableField("date")
    private String date;

    @TableField("completeWork")
    private String completeWork;

    @TableField("nextPlan")
    private String nextPlan;

    @TableField("delay")
    private String delay;
=======
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("weeklypaper")
public class WeeklyPaper {
    private Integer Id;
    //周报标识符
    @TableField(value = "userid")
    private Integer userId;
    //用户Id
    private Date date;
    //日报日期
    @TableField(value = "completework")
    private String completeWork;
    //周报完成的工作
    private String risk;
    //目前的风险
    @TableField(value = "nextplan")
    private String nextPlan;
    //下周计划
    private boolean delay;
    //是否有延迟
>>>>>>> origin/main
}
