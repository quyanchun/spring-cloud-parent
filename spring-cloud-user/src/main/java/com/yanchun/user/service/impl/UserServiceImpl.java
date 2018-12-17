package com.yanchun.user.service.impl;

import com.yanchun.constant.MqConstants;
import com.yanchun.entity.Passport;
import com.yanchun.frombean.RegisterFromBean;
import com.yanchun.user.repository.PersonRepository;
import com.yanchun.user.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
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
    public Long registerUser(RegisterFromBean registerFromBean) throws Exception {
        Passport passport = new Passport();
        passport.setPhone(registerFromBean.getPhone());
        passport.setPassword(registerFromBean.getPassword());
        passport.setRegType(1);
        passport.setCreateTime(new Timestamp(System.currentTimeMillis()));
        passport.setLastTime(new Timestamp(System.currentTimeMillis()));
        passport.setRegWay(1);
        passport = personRepository.save(passport);
        personRepository.flush();
        return passport.getId();
    }
}
