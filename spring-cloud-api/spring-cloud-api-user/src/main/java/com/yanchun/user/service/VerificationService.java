package com.yanchun.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @RequestMapping("/createSmsCode")
    String createSmsCode(@RequestParam("phone") String phone,@RequestParam("type")  Integer type) throws Exception;

    /**
     * 验证短信
     * @param key
     * @param code
     * @param delete 验证后是否删除缓存
     * @return
     * @throws Exception
     */
    @RequestMapping("/matchSmsCode")
    boolean matchSmsCode(@RequestParam("phone")String phone,@RequestParam("key")String key, @RequestParam("code")String code,@RequestParam("delete") Boolean delete) throws Exception;
}
