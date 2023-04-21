package com.hello.weekly.Req;

import lombok.*;

/**
 * 查询课程接口接收数据
 *
 * @author: 漫舞枪神
 * @date: 2023/4/21
 */
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class QueryCourse {
    private String technicalDirection;
    private Integer pageNum;
    private Integer pageSize;
}
