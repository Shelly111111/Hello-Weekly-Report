package com.hello.weekly.Req;

import lombok.*;

/**
 * 更新用户信息接口接收数据
 *
 * @author: 漫舞枪神
 * @date: 2023/4/20
 */
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class UpdateUserInfo {
    private String username;
    private String nickName;
    private String headSculpture;
    private String college;
    private String major;
    private Integer grade;
}
