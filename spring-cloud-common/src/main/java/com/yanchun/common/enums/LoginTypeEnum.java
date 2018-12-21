package com.yanchun.common.enums;
/**
 * 登录和注册方式
 * @author  quyanchun
 * @date    2018/10/29
 */
public enum LoginTypeEnum {
    PHONE(0, "手机号"),
    PHONE_PASSWORD(1, "手机号密码登录"),
    WECHAT(2, "微信"),
    TWITTER(3, "微博"),
    QQ(4, "QQ"),
    EMAIL(5,"email")
    ;
    private Integer type;//登录/注册方式
    private String value;//方式

    private LoginTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    //检测type参数是否存在
    public static boolean checkType(Integer  Type) {
        for (LoginTypeEnum ue : LoginTypeEnum.values()) {
            if (ue.getType().equals(Type)){
                return true;
            }
        }
        return false;
    }
    //通过type获取UserIdentityEnum
    public static LoginTypeEnum getLoginAndRegisterTypeEnum(Integer type) {
        for (LoginTypeEnum ue : LoginTypeEnum.values()) {
            if (ue.getType().equals(type)) {
                return ue;
            }
        }
        return null;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
