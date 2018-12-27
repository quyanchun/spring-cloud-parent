package com.yanchun.common.exception;

import java.io.Serializable;

/**
 * redis查询不到异常
 * @Author quyanchun
 * @Date 2018/12/21
 */
public class VerifycodeException extends  Exception implements Serializable {
    public VerifycodeException(String info){super(info);};
}
