package com.yanchun.user.config;

import com.yanchun.common.base.ResultBase;
import com.yanchun.common.enums.ResultEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 未登录返回json
 * @author  quyanchun
 * @date    2019/1/11
 */
public class MacLoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //如果未登录直接返回 json 未登录
        ResultBase.printResult(ResultEnum.NOLOGIN_ERROR.getCode(), ResultEnum.NOLOGIN_ERROR.getMsg(), null);
    }
}
