package com.hello.oauth.pojo;

import lombok.*;

/**
 * @discription: 对应于数据库中的 User 表
 */
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class User {

    private Integer id;

    private String username;

    private String password;

}