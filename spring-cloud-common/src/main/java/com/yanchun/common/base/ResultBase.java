package com.yanchun.common.base;

import com.yanchun.common.enums.ResultEnum;
import org.springframework.stereotype.Component;

/**
 * @Author quyanchun
 * @Date 2018/12/24
 */
@Component
public class ResultBase {
    // 返回成功
    public static ResponseBase success() {
        return setResult(ResultEnum.OPERATE_SUCCESS, null);
    }

    // 返回成功
    public static ResponseBase success(Object date) {
        return setResult(ResultEnum.OPERATE_SUCCESS, date);
    }

    // 返回失败
    public static ResponseBase systemError() {
        return setResult(ResultEnum.ERROR, null);
    }

    // 返回失败
    public static ResponseBase error(ResultEnum resultEnum) {
        return setResult(resultEnum, null);
    }

    // 通用
    public static ResponseBase setResult(ResultEnum resultEnum, Object data) {
        return new ResponseBase(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    // 通用
    public static ResponseBase setResult(Integer code, String msg, Object data) {
        return new ResponseBase(code, msg, data);
    }

    public static void printResult(Integer code, String msg, Object data) {
        ResponseBase.printResult(code, msg, data);
    }
}
