package com.yanchun.common.advice;

import com.yanchun.common.exception.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SystemControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemControllerAdvice.class);

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }

    /**
     * 拦截捕捉自定义异常 ParamException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ParamException.class)
    public Map myErrorHandler(ParamException ex) {
        LOGGER.error("=========ParamException:{}", ex);
        Map map = new HashMap();
        map.put("code", 4000);
        map.put("msg", ex.getMessage());
        return map;
    }
}