package com.yanchun.user.serviceimpl;

import com.yanchun.entity.Passport;
import com.yanchun.user.dao.PersonRepository;
import com.yanchun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Passport passport= personRepository.findById(id);
        return passport;
    }
}
