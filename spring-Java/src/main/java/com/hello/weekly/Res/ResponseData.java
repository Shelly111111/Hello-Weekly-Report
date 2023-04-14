package com.hello.weekly.Res;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class ResponseData {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

    /**
     * 未找到
     */
    public static final int notFound = 461;
    /**
     * 匹配错误
     */
    public static final int error = 462;
    /**
     * 成功
     */
    public static final int success = 200;

    /***
     * 新旧密码一样
     */
    public static final int warn = 300;


    /***
     * @discription:
     *
     * @param code
     * @param msg
     * @author: Zhang
     * @date: 2023/4/13
     */
    public ResponseData(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
