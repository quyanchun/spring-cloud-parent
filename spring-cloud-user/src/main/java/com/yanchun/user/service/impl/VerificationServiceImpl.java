package com.yanchun.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.yanchun.common.constant.RedisKeyConstants;
import com.yanchun.common.exception.RedisLnvalidException;
import com.yanchun.common.model.Sms;
import com.yanchun.common.utils.CodeUtil;
import com.yanchun.common.utils.RedisUtil;
import com.yanchun.common.utils.SendSmsUtils;
import com.yanchun.user.service.VerificationService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author quyanchun
 * @Date 2018/12/17
 */
@RestController
public class VerificationServiceImpl implements VerificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationServiceImpl.class);
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String createSmsCode(String phone, Integer type) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String code = CodeUtil.randomCode(6);
//        code = "111111";
        Map<String, String> map = new HashMap<>(2);
        map.put("code", code);
        map.put("phone", phone);
        try {
            redisUtil.set(RedisKeyConstants.SMS + uuid, map, RedisKeyConstants.SMS_TIMEOUT);
            if (saveSmsAndSendCode(phone, code, type)) {
                LOGGER.info("==========发送的验证码为：{}", JSON.toJSONString(map));
                return uuid;
            }

        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    @Override
    public boolean matchSmsCode(String phone, String key, String code, Boolean delete) throws Exception {
        key = RedisKeyConstants.SMS + key;
        try {
            Map<String, String> vmap = (Map<String, String>) redisUtil.get(key);
            if (vmap != null) {
                if (vmap.size() == 0) {
                    throw new RedisLnvalidException("==========redisKey失效");
                }
                if (code != null && code.equals(vmap.get("code"))) {
                    LOGGER.info("验证码校验信息：{}", vmap.toString());
                    if (delete == null || delete) {
                        redisUtil.del(key);
                    }
                    if (phone.equals(vmap.get("phone")))
                        return true;
                }
            }
        } catch (Exception e) {
            LOGGER.error("==========matchSmsCode:redis操作失败key:{}", key);
            LOGGER.error("==========Exception：", e);
            throw e;
        }
        return false;
    }

    private boolean saveSmsAndSendCode(String phone, String code, Integer type) throws Exception {

        Sms sms = new Sms();
        sms.setPhone(phone);
        Map<String, String> params = new HashMap<>();
        params.put("code", code);

        sms.setParams(JSON.toJSONString(params));
        SendSmsResponse sendSmsResponse = SendSmsUtils.sendSmsMsg(sms);
        if ("OK".equals(sendSmsResponse.getCode())) {
            return true;
        } else {
            LOGGER.error("==========saveSmsAndSendCode失败：{}。{}", phone, JSON.toJSONString(sendSmsResponse));
        }
        return false;
    }
}
