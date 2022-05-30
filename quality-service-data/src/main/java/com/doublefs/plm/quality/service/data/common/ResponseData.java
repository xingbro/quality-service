package com.doublefs.plm.quality.service.data.common;

/**
 * 公共方法出参
 *
 * @author wujin@doublefs.com
 * @date 2021/11/18
 */

public class ResponseData<T> {

    /**
     * 目前就2种，直接写死了，后边如果多了可以扩展成枚举
     */
    private static final Integer SUCCESS_CODE = 0;
    private static final String SUCCESS_MSG = "SUCCESS";
    private static final Integer ERROR_CODE = -1;
    private static final String ERROR_MSG = "ERROR";

    private Integer code;
    private String msg;
    private T data;

    public static <T> ResponseData<T> success() {
        return results(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public static <T> ResponseData<T> success(String message) {
        return results(SUCCESS_CODE, message, null);
    }

    public static <T> ResponseData<T> success(T data) {
        return results(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> ResponseData<T> success(String mdg, T data) {
        return results(SUCCESS_CODE, mdg, data);
    }

    public static <T> ResponseData<T> fail() {
        return results(ERROR_CODE, ERROR_MSG, null);
    }

    public static <T> ResponseData<T> fail(String message) {
        return results(ERROR_CODE, message, null);
    }

    private static <T> ResponseData<T> results(Integer code, String message, T data) {
        ResponseData<T> result = new ResponseData<>();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}