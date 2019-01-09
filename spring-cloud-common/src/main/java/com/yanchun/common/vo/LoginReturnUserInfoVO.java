package com.yanchun.common.vo;


import lombok.Data;

/**
 * 登录后返回的用户信息
 */
@Data
public class LoginReturnUserInfoVO {
    private String loginName;//用户名
    private String mobile;//手机号
    private Integer userIdentity;//B/C端登录标识
    private Integer type;//用户类型 0普通，1VIP，2白名单，3黑名单
    private String name;//姓名
    private Integer nameVerify;//姓名认证
    private String idCardName;//真实姓名
    private String sign;//个性签名
    private String headImgUrl;//头像
    private String token;//token
    private Integer unReadMessage;//未读消息数
    //用于第三方登陆做返回
    private Boolean userExist;//用户是否存在
    private Boolean isFirstLogin;//用户是否存在
    //微信注册
    private String unionid;//微信id
    private String accessToken;//微信授权token

    private String yunXinToken;//网易云信token
    private String yunXinAccid;//网易云信accid

}
