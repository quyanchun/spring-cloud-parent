package com.yanchun.user.controller;


import com.alibaba.fastjson.JSON;
import com.yanchun.common.base.ResponseBase;
import com.yanchun.common.base.ResultBase;
import com.yanchun.common.entity.Passport;
import com.yanchun.common.enums.RegisterTypeEnum;
import com.yanchun.common.enums.ResultEnum;
import com.yanchun.common.exception.ParamException;
import com.yanchun.common.exception.VerifycodeException;
import com.yanchun.common.frombean.RegisterFromBean;
import com.yanchun.common.utils.PhoneUtil;
import com.yanchun.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @ResponseBody
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
            Passport passport = userService.getPassportByPhone(phone);
            if (passport != null)
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
}
