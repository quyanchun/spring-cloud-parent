package com.yanchun.constant;

/**
 * redis Key 常量
 * （避免冲突）
 *
 * @Author quyanchun
 * @Date 2018/10/29
 */
public interface RedisKeyConstants {
    //token
    String REDIS_USER_SESSION = "aicircle_token:";
    //短信
    String SMS = "sms:";
    //邮箱相关key
    String EMAIL_CODE = "email_code:";
    String EMAIL_URL = "email_url:";

    //key超时时间
    int REDIS_USER_SESSION_TIMEOUT = 60 * 60 * 24 * 30;
    int SMS_TIMEOUT = 60 * 5;
    int EMAIL_URL_TIMEOUT = 60 * 60 * 24;
    int EMAIL_CODE_TIMEOUT = 60 * 10;
}
