package com.yishi.mall.response;

/**
 * 响应码枚举类
 */
public enum ResponseCodeEnum {
    SUCCESS(0, "SUCCESS"),
    REQUEST_ERROR(100, "请求错误"),
    APP_KEY_NOT_FOUND(101, "缺少appKey"),
    SYSTEM_ERROR(999, "系统异常");


    private int code;
    private String msg;

    // 构造方法
    private ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // 普通方法
    public static String getMsg(int code) {
        for (ResponseCodeEnum c : ResponseCodeEnum.values()) {
            if (code == c.getCode()) {
                return c.msg;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
