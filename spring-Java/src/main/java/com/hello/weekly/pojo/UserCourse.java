package com.hello.weekly.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/***
 * 数据库中的UserCourse
 */

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@TableName("UserCourse")
public class UserCourse {

    @JsonIgnore
    @JSONField(serialize = false)
    private Integer Id;

    @JsonIgnore
    @TableField("userId")
    private Integer userId;

    @JsonIgnore
    @TableField("courseId")
    private Integer courseId;

}
