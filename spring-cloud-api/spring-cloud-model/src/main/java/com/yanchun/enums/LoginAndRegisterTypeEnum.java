package com.yanchun.enums;
/**
 * 登录和注册方式
 * @author  quyanchun
 * @date    2018/10/29
 */
public enum LoginAndRegisterTypeEnum {
    PHONE(0, "手机号"),
    WECHAT(1, "微信"),
    TWITTER(2, "微博"),
    QQ(3, "QQ"),
    PHONE_PASSWORD(4, "手机号密码登录"),
    ;
    private Integer type;//登录/注册方式
    private String value;//方式

    private LoginAndRegisterTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    //检测type参数是否存在
    public static boolean checkType(Integer  Type) {
        for (LoginAndRegisterTypeEnum ue : LoginAndRegisterTypeEnum.values()) {
            if (ue.getType().equals(Type)){
                return true;
            }
        }
        return false;
    }
    //通过type获取UserIdentityEnum
    public static LoginAndRegisterTypeEnum getLoginAndRegisterTypeEnum(Integer type) {
        for (LoginAndRegisterTypeEnum ue : LoginAndRegisterTypeEnum.values()) {
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
