package com.hello.weekly.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.Year;

/**
 * @discription: 对应于数据库中的 UserInformation 表
 * @author: Zhang
 * @date: 2023/4/14
 */
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@TableName("UserInformation")
public class UserInfo {

    private Integer Id;
    /***
     * 昵称
     */
    @TableField("nickName")
    private String nickName;
    /***
     * 头像，base64
     */
    @TableField("headSculpture")
    private String headSculpture;
    /***
     * 学院
     */
    private String college;
    /***
     * 专业
     */
    private String major;
    /***
     * 年级
     */
    private Integer grade;

}
