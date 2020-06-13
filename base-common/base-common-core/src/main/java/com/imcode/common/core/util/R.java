package com.imcode.common.core.util;

import com.imcode.common.core.constant.CommonConstants;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class R<T> implements Serializable {



    private Integer code;

    private Boolean success;

    private T data;

    private String msg;


    public static <T> R<T> ok() {
        return restResult(null,  true ,  CommonConstants.SUCCESS, null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, true ,CommonConstants.SUCCESS, null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, true , CommonConstants.SUCCESS, msg);
    }

    public static <T> R<T> failed() {
        return restResult(null,  false ,CommonConstants.FAIL, null);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null,  false , CommonConstants.FAIL, msg);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, false ,CommonConstants.FAIL, null);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data,false ,CommonConstants.FAIL, msg);
    }

    public static <T> R<T> failed(T data, int code, String msg) {
        return restResult(data, false ,code, msg);
    }

    public static <T> R<T> failed(int code, String msg) {
        return restResult(null, false ,code, msg);
    }


    private static <T> R<T> restResult(T data , Boolean success , Integer code , String msg){

        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        r.setSuccess(success);
        return r;
    }


}
