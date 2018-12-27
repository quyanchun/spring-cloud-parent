package com.yanchun.controller;

import com.yanchun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author quyanchun
 * @Date 2018/12/25
 */
@RestController
public class TestController {
    @Autowired
    private UserService userService;
    @RequestMapping("/test/tt")
    public String  getTest(){

        String test = userService.getTest();
        return "success";
    }
}
