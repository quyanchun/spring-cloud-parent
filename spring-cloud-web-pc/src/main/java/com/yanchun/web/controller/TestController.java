package com.yanchun.web.controller;

import com.yanchun.user.service.UserService;
import com.yanchun.web.feign.UserServiceFeign;
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
    private UserServiceFeign userServiceFeign;
    @RequestMapping("/test/tt")
    public String  getTest(){
        return "success";
    }
}
