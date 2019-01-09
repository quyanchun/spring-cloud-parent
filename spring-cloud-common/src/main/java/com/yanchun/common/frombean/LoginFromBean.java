package com.yanchun.common.frombean;

import lombok.Data;

/**
 * @Author quyanchun
 * @Date 2018/12/11
 */
@Data
public class LoginFromBean {
    private String smsCode;//手机验证码
    private String key;//验证码对应的key
    private String phone;//手机号
    private String password;//密码
    private Integer userIdentity;//B/C端用户标识
    private Integer type;//注册方式
    private Integer sourceType;//注册来源

    //登陆（并绑定微信）
    private String unionid;//微信id
    private String accessToken;//微信授权token
}
