package com.yanchun.user.controller;


import com.alibaba.fastjson.JSON;
import com.yanchun.common.base.ResponseBase;
import com.yanchun.common.base.ResultBase;
import com.yanchun.common.dto.PassportDTO;
import com.yanchun.common.entity.Passport;
import com.yanchun.common.enums.LoginTypeEnum;
import com.yanchun.common.enums.RegisterTypeEnum;
import com.yanchun.common.enums.ResultEnum;
import com.yanchun.common.exception.ParamException;
import com.yanchun.common.exception.VerifycodeException;
import com.yanchun.common.frombean.LoginFromBean;
import com.yanchun.common.frombean.RegisterFromBean;
import com.yanchun.common.model.UserSession;
import com.yanchun.common.utils.PhoneUtil;
import com.yanchun.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;
import sun.security.util.Password;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author quyanchun
 * @Date 2018/12/6
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/register")
    public ResponseBase register(@RequestBody RegisterFromBean registerFromBean) throws Exception {
        try {
            //验证参数
            String phone = registerFromBean.getPhone();
            String smsCode = registerFromBean.getSmsCode();
            String key = registerFromBean.getKey();
            String password = registerFromBean.getPassword();
            Integer type = registerFromBean.getType();
            //1.判断账号是否已被注册
            PassportDTO passportDTO = userService.getPassportByPhone(phone);
            if (passportDTO != null)
                return ResultBase.error(ResultEnum.PHONE_REGISTER_ERROR);
            if (!RegisterTypeEnum.checkType(type) || !PhoneUtil.checkPhone(phone))
                return ResultBase.error(ResultEnum.PARAMETER_ERROR);
            //2.进行注册
            Long passportId = userService.registerUser(phone, smsCode, key, password, type);
            if (passportId != null)
                return ResultBase.success();
        } catch (ParamException e) {
            LOGGER.error("==========register参数错误:{}", JSON.toJSONString(registerFromBean));
            return ResultBase.error(ResultEnum.PARAMETER_ERROR);
        } catch (VerifycodeException e) {
            LOGGER.error("==========register验证码错误:{}", JSON.toJSONString(registerFromBean));
            return ResultBase.error(ResultEnum.CODE_ERROR);
        } catch (Exception e) {
            LOGGER.error("==========Exception:", e);
        }
        return ResultBase.systemError();
    }

    @RequestMapping("/login")
    public ResponseBase login(@RequestBody LoginFromBean loginFromBean, HttpServletRequest request) throws Exception {
        try {
            UserSession userSession = null;
            //验证参数
            String phone = loginFromBean.getPhone();
            //1.判断账号是否已被注册
            PassportDTO passportDTO = userService.getPassportByPhone(phone);
            if (passportDTO == null)
                return ResultBase.error(ResultEnum.PHONE_UNREGISTER_ERROR);
            passportDTO = userService.login(loginFromBean);
//            Map<String, String> parameters = new HashMap<>();
//            parameters.put(OAuth2Utils.GRANT_TYPE, "password");
//            parameters.put(OAuth2Utils.CLIENT_ID, "system");
//            parameters.put("client_secret", "system");
//            parameters.put(OAuth2Utils.SCOPE, "app");
//            // 为了支持多类型登录，这里在username后拼装上登录类型
//            parameters.put("username", loginFromBean.getPhone() + "|" + LoginTypeEnum.PHONE_PASSWORD.name());
//            parameters.put("password", loginFromBean.getPassword());


            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(passportDTO.getPhone(),passportDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(authRequest); //调用loadUserByUsername
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非
            userSession = (UserSession) authentication.getDetails();

            return ResultBase.success(passportDTO);
        } catch (ParamException e) {
            LOGGER.error("==========register参数错误:{}", JSON.toJSONString(loginFromBean));
            return ResultBase.error(ResultEnum.PARAMETER_ERROR);
        } catch (VerifycodeException e) {
            LOGGER.error("==========register验证码错误:{}", JSON.toJSONString(loginFromBean));
            return ResultBase.error(ResultEnum.CODE_ERROR);
        } catch (Exception e) {
            LOGGER.error("==========Exception:", e);
        }
        return ResultBase.systemError();
    }

    @RequestMapping("/unlogin")
    public ResponseBase unlogin() throws Exception {
        try {
            return ResultBase.error(ResultEnum.NOLOGIN_ERROR);
        } catch (Exception e) {
            LOGGER.error("==========Exception:", e);
        }
        return ResultBase.systemError();
    }
}
