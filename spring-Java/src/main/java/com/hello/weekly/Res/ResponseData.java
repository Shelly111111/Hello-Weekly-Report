package com.hello.weekly.Res;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
public class ResponseData {

    //状态码
    private Integer code;
    //消息
    private String message;
    //数据
    private Object data;

    public ResponseData(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
