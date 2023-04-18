package com.hello.weekly.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dailypaper")
//有时候关联属性字段与表结构不一致，导致使用时报错
public class DailyPaper {
    @TableId(type = IdType.AUTO)
    @JsonIgnore
    private Integer Id;
    //日报标识符，自增
    @JsonIgnore
    @TableField(value = "userid")
    private Integer userId;
    //用户Id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    //日报日期
    @TableField(value = "completework")
    private String completeWork;
    //完成的工作
    private String risk;
    //目前的风险
    private Boolean delay;
    //是否有延迟
}
