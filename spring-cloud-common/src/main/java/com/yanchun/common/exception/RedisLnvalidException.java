package com.yanchun.common.exception;

/**
 * redis查询不到异常
 * @Author quyanchun
 * @Date 2018/12/21
 */
public class RedisLnvalidException extends  Exception {
    public RedisLnvalidException(String info){super(info);};
}
