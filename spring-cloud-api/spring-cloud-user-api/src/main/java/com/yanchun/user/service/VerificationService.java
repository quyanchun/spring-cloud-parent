package com.yanchun.user.service;

/**
 * @Author quyanchun
 * @Date 2018/12/17
 */
public interface VerificationService {
    /**
     * 生成短信验证码
     *
     * @param mobile
     * @param type
     * @return
     * @throws Exception
     */
    String createSmsCode(String mobile, Integer type) throws Exception;
}
