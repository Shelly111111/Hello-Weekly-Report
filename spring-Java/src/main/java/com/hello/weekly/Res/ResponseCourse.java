package com.hello.weekly.Res;

import com.hello.weekly.pojo.UserCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCourse {

    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据 list
     */
    private List<UserCourse> data;
}
