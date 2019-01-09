package com.yanchun.user.service.impl;

import com.yanchun.common.base.ResultBase;
import com.yanchun.common.entity.Passport;
import com.yanchun.common.enums.LoginTypeEnum;
import com.yanchun.common.enums.RegisterTypeEnum;
import com.yanchun.common.enums.ResultEnum;
import com.yanchun.common.exception.ParamException;
import com.yanchun.common.exception.VerifycodeException;
import com.yanchun.common.frombean.LoginFromBean;
import com.yanchun.common.utils.MD5;
import com.yanchun.common.utils.PhoneUtil;
import com.yanchun.common.vo.LoginReturnUserInfoVO;
import com.yanchun.user.repository.UserRepository;
import com.yanchun.user.service.UserService;
import com.yanchun.user.service.VerificationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.sql.Timestamp;


/**
 * @Author quyanchun
 * @Date 2018/12/6
 */
@RestController
public class UserServiceImpl extends ResultBase implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationService verificationService;

    @Override
    public Passport getPassportById(long id) throws Exception {
        Passport passport = userRepository.findById(id).get();

        return passport;
    }

    @Override
    public Passport getPassportByPhone(String phone) throws Exception {
        return userRepository.findByPhone(phone);
    }

    @Override
    @Transactional
    public Long registerUser(String phone, String smsCode, String key, String password, Integer type) throws Exception {
        RegisterTypeEnum registerTypeEnum = RegisterTypeEnum.getRegisterTypeEnum(type);
        switch (registerTypeEnum) {
            case PHONE:
                if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(smsCode) || StringUtils.isEmpty(key) || StringUtils.isEmpty(password))
                    throw new ParamException("参数错误");
                boolean flag = verificationService.matchSmsCode(phone, key, smsCode, false);
                if (!flag)
                    throw new VerifycodeException("验证码错误");
                Passport passport = new Passport();
                passport.setPhone(phone);
                password = MD5.encode(password);
                passport.setPassword(password);
                passport.setRegType(1);
                passport.setCreateTime(new Timestamp(System.currentTimeMillis()));
                passport.setLastTime(new Timestamp(System.currentTimeMillis()));
                passport.setRegWay(1);
                passport = userRepository.save(passport);
                userRepository.flush();
                return passport.getId();
        }
        return null;
    }

    @Override
    public Passport login(LoginFromBean loginFromBean) throws Exception {
        Passport passport = null;
        switch (LoginTypeEnum.getLoginTypeEnum(loginFromBean.getType())) {
            //手机验证码登陆
            case PHONE:
                if (!PhoneUtil.checkPhone(loginFromBean.getPhone()))
                    throw new ParamException("手机号不合法");
                passport = userRepository.findByPhone(loginFromBean.getPhone());
                boolean flag = verificationService.matchSmsCode(loginFromBean.getPhone(), loginFromBean.getKey(), loginFromBean.getSmsCode(), false);
                if (!flag) {
                    printResult(ResultEnum.CODE_ERROR.getCode(), ResultEnum.CODE_ERROR.getMsg(), null);
                    return null;
                }
                return passport;
            //账户密码登陆
            case PHONE_PASSWORD:
                if (!PhoneUtil.checkPhone(loginFromBean.getPhone()))
                    throw new ParamException("手机号不合法");
                passport = userRepository.findByPhone(loginFromBean.getPhone());

                String insPassword = MD5.encode(loginFromBean.getPassword());

                return passport;
            //微信登陆
            case WECHAT:
                String accessToken = loginFromBean.getAccessToken();
                String unionid = loginFromBean.getUnionid();
                if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(unionid))
                    throw new ParamException("accessToken、unionid不能为空");
                passport = userRepository.findByUnionid(unionid);
                if (passport == null) {
                    //返回微信授权信息
                    LoginReturnUserInfoVO loginReturnUserInfoVO = new LoginReturnUserInfoVO();
                    loginReturnUserInfoVO.setUserExist(false);
                    loginReturnUserInfoVO.setUnionid(accessToken);
                    loginReturnUserInfoVO.setAccessToken(unionid);
                    printResult(ResultEnum.OPERATE_SUCCESS.getCode(), ResultEnum.OPERATE_SUCCESS.getMsg(), loginReturnUserInfoVO);
                }
                return passport;
            default:
                throw new ParamException("type参数错误");
        }
    }

}
