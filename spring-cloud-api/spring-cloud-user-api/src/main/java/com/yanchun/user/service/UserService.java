package com.yanchun.user.service;

import com.yanchun.entity.Passport;

/**
 * @Author quyanchun
 * @Date 2018/12/6
 */
public interface UserService {
    Passport getPassportById(long id);
}