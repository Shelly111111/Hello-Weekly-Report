package com.hello.weekly.Req.Paper;

import lombok.*;

/**
 * 获取日报接口接收数据
 *
 * @author: 漫舞枪神
 * @date: 2023/4/21
 */
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class GetPaper {
    private Integer currentpage;
    private Integer size;
    private String username;
}
