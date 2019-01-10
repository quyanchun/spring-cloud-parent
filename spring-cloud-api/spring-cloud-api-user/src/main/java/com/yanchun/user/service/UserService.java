package com.yanchun.user.service;


import com.yanchun.common.dto.PassportDTO;
import com.yanchun.common.frombean.LoginFromBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @RequestMapping("/getPassportById")
    PassportDTO getPassportById(@RequestParam("id") long id) throws Exception;

    /**
     * 通过phone获取passport
     *
     * @param phone
     * @return
     * @throws Exception
     */
    @RequestMapping("/getPassportByPhone")
    PassportDTO getPassportByPhone(@RequestParam("phone") String phone) throws Exception;

    /**
     * 注册
     *
     * @param phone
     * @param smsCode
     * @param key
     * @param password
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping("/registerUser")
    Long registerUser(@RequestParam("phone") String phone, @RequestParam("smsCode") String smsCode, @RequestParam("key") String key, @RequestParam("password") String password, @RequestParam("type") Integer type) throws Exception;

    /**
     * 登陆
     * @param loginFromBean
     * @return
     * @throws Exception
     */
    PassportDTO login(LoginFromBean loginFromBean) throws Exception;

}
