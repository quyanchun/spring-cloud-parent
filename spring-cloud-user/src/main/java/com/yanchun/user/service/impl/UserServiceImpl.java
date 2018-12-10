package com.yanchun.user.service.impl;

import com.yanchun.entity.Passport;
import com.yanchun.user.repository.PersonRepository;
import com.yanchun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Passport> byId = personRepository.findById(id);
        Passport passport = byId.get();
        Passport byPhone = personRepository.findByPhone("12121");
        List<Passport> all = personRepository.findAll();
        return passport;
    }
}
