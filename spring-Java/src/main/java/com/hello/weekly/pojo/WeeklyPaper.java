package com.hello.weekly.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("weeklypaper")
public class WeeklyPaper {
    @TableId(type = IdType.AUTO)
    @JsonIgnore
    private Integer Id;
    //周报标识符
    @TableField(value = "userid")
    @JsonIgnore
    private Integer userId;
    //用户Id
    private Date date;
    //周报日期
    @TableField(value = "completework")
    private String completeWork;
    //周报完成的工作
    private String risk;
    //目前的风险
    @TableField(value = "nextplan")
    private String nextPlan;
    //下周计划
    private Boolean delay;
    //是否有延迟
}
