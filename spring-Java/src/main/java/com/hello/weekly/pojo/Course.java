package com.hello.weekly.pojo;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;


/***
 * 数据库中的Course
 */
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@JsonIgnoreProperties({"id"})
public class Course {

    @JsonIgnore

    @JSONField(serialize = false)
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
