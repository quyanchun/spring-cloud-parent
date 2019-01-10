package com.yanchun.common.enums;

import lombok.Getter;
@Getter
public enum ResultEnum {
    OPERATE_SUCCESS(1000, "操作成功"),
    PARAMETER_ERROR(4000, "参数错误"),
    NOLOGIN_ERROR(4001, "未登录"),
    CODE_ERROR(4002, "验证码错误"),
    PHONE_REGISTER_ERROR(4003, "账号已注册"),
    PHONE_UNREGISTER_ERROR(4004, "账号未注册"),

    SING_ERROR(9000,"签名错误"),
    OPERATE_ERROR(9996, "操作失败"),
    ERROR(9999, "系统错误");
    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
