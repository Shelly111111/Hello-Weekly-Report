package com.hello.weekly.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TableName(value ="IdleTime")
public class IdleTime {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userid;

    private Date date;

    private String time;

    private Boolean idle;


}
