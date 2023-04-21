package com.hello.weekly.Req;

import lombok.*;

/***
 * 更新密码的接口数据
 */
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class ChangeInfo {
    private String username;
    private String oldpassword;
    private String newpassword;
}
