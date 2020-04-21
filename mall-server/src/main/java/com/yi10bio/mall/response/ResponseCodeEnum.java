package com.yi10bio.mall.response;

/**
 * 响应码枚举类
 */
public enum ResponseCodeEnum {

    SUCCESS("0", "SUCCESS"), REQUEST_ERROR("100", "请求错误"), APP_KEY_NOT_FOUND("101", "缺少appKey");



    private String code;
    private String msg;

    // 构造方法
    private ResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // 普通方法
    public static String getMsg(String code) {
        for (ResponseCodeEnum c : ResponseCodeEnum.values()) {
            if (code.equals(c.getCode()) ) {
                return c.msg;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
