package com.yanchun.user.service;

/**
 * @Author quyanchun
 * @Date 2018/12/17
 */
public interface VerificationService {
    /**
     * 生成短信验证码
     *
     * @param phone
     * @param type
     * @return
     * @throws Exception
     */
    String createSmsCode(String phone, Integer type) throws Exception;

    /**
     * 验证短信
     * @param key
     * @param code
     * @param delete 验证后是否删除缓存
     * @return
     * @throws Exception
     */
    boolean matchSmsCode(String phone,String key, String code, Boolean delete) throws Exception;
}
