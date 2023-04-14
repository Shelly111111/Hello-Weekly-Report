package com.hello.weekly.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@TableName(value ="IdleTime")
public class IdleTime {

    private Integer id;

    private Integer userid;

    private Date date;

    private Time time;

    private Boolean idle;

}
