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
import com.yanchun.common.utils.PhoneUtil;
import com.yanchun.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author quyanchun
 * @Date 2018/12/6
 */
@RestController
@RequestMapping("/user")
public class UserController extends ResultBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

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
                return error(ResultEnum.PHONE_REGISTER_ERROR);
            if (!RegisterTypeEnum.checkType(type) || !PhoneUtil.checkPhone(phone))
                return error(ResultEnum.PARAMETER_ERROR);
            //2.进行注册
            Long passportId = userService.registerUser(phone, smsCode, key, password, type);
            if (passportId != null)
                return success();
        } catch (ParamException e) {
            LOGGER.error("==========register参数错误:{}", JSON.toJSONString(registerFromBean));
            return error(ResultEnum.PARAMETER_ERROR);
        } catch (VerifycodeException e) {
            LOGGER.error("==========register验证码错误:{}", JSON.toJSONString(registerFromBean));
            return error(ResultEnum.CODE_ERROR);
        } catch (Exception e) {
            LOGGER.error("==========Exception:", e);
        }
        return systemError();
    }

    @RequestMapping("/login")
    public ResponseBase login(@RequestBody LoginFromBean loginFromBean) throws Exception {
        try {
            //验证参数
            String phone = loginFromBean.getPhone();
            //1.判断账号是否已被注册
            PassportDTO passportDTO = userService.getPassportByPhone(phone);
            if (passportDTO == null)
                return error(ResultEnum.PHONE_UNREGISTER_ERROR);
              passportDTO = userService.login(loginFromBean);
            Map<String, String> parameters = new HashMap<>();
            parameters.put(OAuth2Utils.GRANT_TYPE, "password");
            parameters.put(OAuth2Utils.CLIENT_ID, "system");
            parameters.put("client_secret", "system");
            parameters.put(OAuth2Utils.SCOPE, "app");
//		parameters.put("username", username);
            // 为了支持多类型登录，这里在username后拼装上登录类型
            parameters.put("username", loginFromBean.getPhone() + "|" + LoginTypeEnum.PHONE_PASSWORD.name());
            parameters.put("password", loginFromBean.getPassword());
//            tokenEndpoint.postAccessToken(parameters);
            return success(passportDTO);
        } catch (ParamException e) {
            LOGGER.error("==========register参数错误:{}", JSON.toJSONString(loginFromBean));
            return error(ResultEnum.PARAMETER_ERROR);
        } catch (VerifycodeException e) {
            LOGGER.error("==========register验证码错误:{}", JSON.toJSONString(loginFromBean));
            return error(ResultEnum.CODE_ERROR);
        } catch (Exception e) {
            LOGGER.error("==========Exception:", e);
        }
        return systemError();
    }
    @RequestMapping("/unlogin")
    public ResponseBase unlogin() throws Exception {
        try {
           return error(ResultEnum.NOLOGIN_ERROR);
        } catch (Exception e) {
            LOGGER.error("==========Exception:", e);
        }
        return systemError();
    }
}
