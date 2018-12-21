package com.yanchun.common.enums;
/**
 * 短信用途
 * @author  quyanchun
 * @date    2018/10/29
 */
public enum SmsUsedEnum {
    OTHER(0,"其他"),
    REGISTER(1,"注册"),
    LOGIN(2,"登录"),
    EDIT_PASSWORD(3,"修改密码")
    ;
    private Integer type;//用途类型
    private String value;//用途
    private SmsUsedEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
