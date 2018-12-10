package com.yanchun.user.controller;


import com.yanchun.entity.Passport;
import com.yanchun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author quyanchun
 * @Date 2018/12/6
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/user/getPassport")
    public Passport getPassport(@RequestParam("id") Long id){
        return userService.getPassportById(id);
    }
}
