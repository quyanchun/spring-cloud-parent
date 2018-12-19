package com.yanchun.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.yanchun.constant.RedisKeyConstants;
import com.yanchun.model.Sms;
import com.yanchun.user.service.VerificationService;
import com.yanchun.utils.CodeUtil;
import com.yanchun.utils.RedisUtil;
import com.yanchun.utils.SendSmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author quyanchun
 * @Date 2018/12/17
 */
@Service
public class VerificationServiceImpl implements VerificationService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String createSmsCode(String mobile, Integer type) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String code = CodeUtil.randomCode(6);
        code = "111111";
        Map<String, String> map = new HashMap<>(2);
        map.put("code", code);
        map.put("mobile", mobile);
        try {
            redisUtil.set(RedisKeyConstants.SMS+uuid, map, RedisKeyConstants.SMS_TIMEOUT);
//            if (saveSmsAndSendCode(mobile, code, type))
                return uuid;
        } catch (Exception e) {
            throw e;
        }
//        return null;
    }

    private boolean saveSmsAndSendCode(String mobile, String code, Integer type) throws Exception {

        Sms sms = new Sms();
        sms.setMobile(mobile);
        Map<String, String> params = new HashMap<>();
        params.put("code", code);

        sms.setParams(JSON.toJSONString(params));
        SendSmsResponse sendSmsResponse = SendSmsUtils.sendSmsMsg(sms);
        if ("OK".equals(sendSmsResponse.getCode())) {
            return true;
        }
        return false;
    }
}
