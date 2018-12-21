package com.yanchun.user.service.impl;

import com.yanchun.common.entity.Passport;
import com.yanchun.common.exception.ParamException;
import com.yanchun.common.frombean.RegisterFromBean;
import com.yanchun.user.repository.PersonRepository;
import com.yanchun.user.service.UserService;
import com.yanchun.user.service.VerificationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @Author quyanchun
 * @Date 2018/12/6
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private VerificationService verificationService;
    @Override
    public Passport getPassportById(long id) throws Exception {
        Optional<Passport> byId = personRepository.findById(id);
        Passport passport = byId.get();
        Passport byPhone = personRepository.findByPhone("12121");
        List<Passport> all = personRepository.findAll();
        return passport;
    }

    @Override
    @Transactional
    public boolean registerUser(RegisterFromBean registerFromBean) throws Exception {
        String phone = registerFromBean.getPhone();
        String smsCode = registerFromBean.getSmsCode();
        String key = registerFromBean.getKey();
        if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(phone)||StringUtils.isEmpty(phone)||StringUtils.isEmpty(phone))
            throw new ParamException("参数错误");
        boolean flag = verificationService.matchSmsCode(phone, key, smsCode, false);
        if(!flag)
            return false;
        Passport passport = new Passport();
        passport.setPhone(phone);
        passport.setPassword(registerFromBean.getPassword());
        passport.setRegType(1);
        passport.setCreateTime(new Timestamp(System.currentTimeMillis()));
        passport.setLastTime(new Timestamp(System.currentTimeMillis()));
        passport.setRegWay(1);
        passport = personRepository.save(passport);
        personRepository.flush();
        return true;
    }
}
