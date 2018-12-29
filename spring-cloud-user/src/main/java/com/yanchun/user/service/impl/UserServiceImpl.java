package com.yanchun.user.service.impl;

import com.yanchun.common.entity.Passport;
import com.yanchun.common.enums.RegisterTypeEnum;
import com.yanchun.common.exception.ParamException;
import com.yanchun.common.exception.VerifycodeException;
import com.yanchun.user.repository.PersonRepository;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private VerificationService verificationService;

    @Override
    public Passport getPassportById(long id) throws Exception {
        Passport passport = personRepository.findById(id).get();

        return passport;
    }

    @Override
    public Passport getPassportByPhone(String phone) throws Exception {
        return personRepository.findByPhone(phone);
    }
    @Override
    @Transactional
    public Long registerUser(String phone, String smsCode, String key, String password, Integer type) throws Exception {
        RegisterTypeEnum registerTypeEnum = RegisterTypeEnum.getRegisterTypeEnum(type);
        switch (registerTypeEnum){
            case PHONE:
                if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(smsCode)||StringUtils.isEmpty(key)||StringUtils.isEmpty(password))
                    throw new ParamException("参数错误");
                boolean flag = verificationService.matchSmsCode(phone, key, smsCode, false);
                if (!flag)
                    throw new VerifycodeException("验证码错误");
                Passport passport = new Passport();
                passport.setPhone(phone);
                passport.setPassword(password);
                passport.setRegType(1);
                passport.setCreateTime(new Timestamp(System.currentTimeMillis()));
                passport.setLastTime(new Timestamp(System.currentTimeMillis()));
                passport.setRegWay(1);
                passport = personRepository.save(passport);
                personRepository.flush();
                return passport.getId();
        }
        return null;
    }

}
