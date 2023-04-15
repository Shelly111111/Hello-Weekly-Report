package com.hello.weekly.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dailypaper")
//有时候关联属性字段与表结构不一致，导致使用时报错
public class DailyPaper {
    private int Id;
    //日报标识符
    @TableField(value = "userid")
    private int userId;
    //用户Id
    private Date date;
    //日报日期
    @TableField(value = "completework")
    private String completeWork;
    //完成的工作
    private String risk;
    //目前的风险
    private boolean delay;
    //是否有延迟
}
