package com.hello.weekly.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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

    private Integer Id;

    @TableField("userId")
    private Integer userId;

    @TableField("courseId")
    private Integer courseId;

}
