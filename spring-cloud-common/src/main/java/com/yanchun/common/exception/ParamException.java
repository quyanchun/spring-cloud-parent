package com.yanchun.common.exception;

/**
 * redis查询不到异常
 * @Author quyanchun
 * @Date 2018/12/21
 */
public class ParamException extends  Exception {
    public ParamException(String info){super(info);};
}
