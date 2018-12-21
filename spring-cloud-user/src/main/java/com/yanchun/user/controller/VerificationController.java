package com.yanchun.user.controller;

import com.alibaba.fastjson.JSON;
import com.yanchun.common.frombean.MatchCodeFromBean;
import com.yanchun.user.service.VerificationService;
import com.yanchun.user.service.impl.VerificationServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.image.IntegerComponentRaster;

/**
 * @Author quyanchun
 * @Date 2018/12/17
 */
@RestController
@RequestMapping("/verification")
public class VerificationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationController.class);
    @Autowired
    private VerificationService verificationService;

    @RequestMapping("/sendSmsCode")
    public String sendSmsCode(@RequestParam("phone") String phone, @RequestParam("type") int type) {
        try {
            return verificationService.createSmsCode(phone, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/matchSmsCode")
    public String matchSmsCode(@RequestBody MatchCodeFromBean matchCodeFromBean) {
        try {
            boolean flag = verificationService.matchSmsCode(matchCodeFromBean.getPhone(), matchCodeFromBean.getKey(), matchCodeFromBean.getSmsCode(), false);
            if (flag)
                return "成功";

            return "失败";
        } catch (Exception e) {
            LOGGER.error("==========短信验证失败：{}", JSON.toJSONString(matchCodeFromBean));
            LOGGER.error("==========Exception:", e);
        }
        return null;
    }
}
