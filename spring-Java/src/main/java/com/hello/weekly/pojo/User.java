package com.hello.weekly.pojo;

import lombok.*;

/**
 * 对应于数据库中的 User 表
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private Integer id;

    private String username;

    private String password;

}