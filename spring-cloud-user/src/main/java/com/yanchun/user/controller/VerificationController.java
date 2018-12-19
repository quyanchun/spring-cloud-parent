package com.yanchun.user.controller;

import com.yanchun.enums.SmsUsedEnum;
import com.yanchun.user.service.VerificationService;
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
}
