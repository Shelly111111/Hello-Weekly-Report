package com.hello.weekly.Res;

public class ResponseData {

    //状态码
    public Integer code;
    //消息
    public String message;
    //数据
    public Object data;


    public ResponseData(Integer code, String msg, Object data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public ResponseData(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
