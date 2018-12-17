package com.yanchun.user.service;


import com.yanchun.entity.Passport;
import com.yanchun.frombean.RegisterFromBean;

/**
 * @Author quyanchun
 * @Date 2018/12/6
 */
public interface UserService {
    /**
     * 获取passport表
     *
     * @param id
     * @return
     */
    Passport getPassportById(long id) throws Exception;

    /**
     * 注册
     * @param registerFromBean
     * @return
     * @throws Exception
     */
    Long registerUser(RegisterFromBean registerFromBean) throws Exception;
}
