package com.yanchun.user.service.impl;

import com.yanchun.user.dao.PersonRepository;
import com.yanchun.user.entity.Passport;
import com.yanchun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author quyanchun
 * @Date 2018/12/6
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PersonRepository personRepository;
    @Override
    public Passport getPassportById(long id) {
        Passport passport = (Passport) personRepository.findById(id);
        return passport;
    }
}
