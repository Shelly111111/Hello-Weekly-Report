package com.hello.weekly.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/***
 * 数据库中的Course
 */
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Course {

    private Integer id;

    private String title;

    @TableField("description")
    private String description;

    @TableField("dateTime")
    private String dateTime;


    @TableField("technicalDirection")
    private  String technicalDirection;


    @TableField("level")
    private String level;


    @TableField("mode")
    private String mode;

}
