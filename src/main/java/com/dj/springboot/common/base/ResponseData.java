package com.dj.springboot.common.base;

import com.dj.springboot.common.enums.ResponseCode;
import lombok.Data;

@Data
public class ResponseData {
    private int code = 200;
    private String message = "";
    private Object data;

    public static ResponseData ok(Object data) {
        return new ResponseData("处理成功", data);
    }

    public static ResponseData fail(String message) {
        return new ResponseData(ResponseCode.BAD_REQUEST.getCode(), message);
    }

    public static ResponseData fail(String message, Object data) {
        return new ResponseData(ResponseCode.BAD_REQUEST.getCode(), message, data);
    }

    public static ResponseData fail(int code, String message) {
        return new ResponseData(code, message);
    }

    public static ResponseData fail(int code, String message, Object data) {
        return new ResponseData(ResponseCode.BAD_REQUEST.getCode(), message, data);
    }


    public ResponseData(Object data) {
        super();
        this.data = data;
    }

    public ResponseData(String message) {
        super();
        this.message = message;
    }

    public ResponseData(int code, String message) {
        super();
        this.message = message;
        this.code = code;
    }

    public ResponseData(int code, Object data) {
        super();
        this.data = data;
        this.code = code;
    }

    public ResponseData(String message, Object data) {
        super();
        this.message = message;
        this.data = data;
    }

    public ResponseData(int code, String message, Object data) {
        super();
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ResponseData() {
        super();
    }

}
