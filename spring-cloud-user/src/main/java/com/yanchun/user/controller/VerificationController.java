package com.yanchun.user.controller;

import com.alibaba.fastjson.JSON;
import com.yanchun.common.base.ResponseBase;
import com.yanchun.common.base.ResultBase;
import com.yanchun.common.enums.ResultEnum;
import com.yanchun.common.frombean.MatchCodeFromBean;
import com.yanchun.user.service.VerificationService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author quyanchun
 * @Date 2018/12/17
 */
@RestController
@RequestMapping("/verification")
public class VerificationController extends ResultBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationController.class);
    @Autowired
    private VerificationService verificationService;

    @RequestMapping("/sendSmsCode")
    public ResponseBase sendSmsCode(@RequestParam("phone") String phone, @RequestParam("type") int type) {
        try {
            String key = verificationService.createSmsCode(phone, type);
            if(StringUtils.isEmpty(key))
                return error(ResultEnum.OPERATE_ERROR);
            HashMap hashMap = new HashMap();
            hashMap.put("key", key);
            return success(hashMap);
        } catch (Exception e) {
            LOGGER.error("==========短信发送失败",e);
        }
        return null;
    }

    @RequestMapping("/matchSmsCode")
    public ResponseBase matchSmsCode(@RequestBody MatchCodeFromBean matchCodeFromBean) {
        try {
            boolean flag = verificationService.matchSmsCode(matchCodeFromBean.getPhone(), matchCodeFromBean.getKey(), matchCodeFromBean.getSmsCode(), false);
            if (flag)
               return success();
            return error(ResultEnum.CODE_ERROR);
        } catch (Exception e) {
            LOGGER.error("==========短信验证失败：{}", JSON.toJSONString(matchCodeFromBean));
            LOGGER.error("==========Exception:", e);
        }
        return null;
    }
}
