package com.yanchun.user.controller;


import com.yanchun.entity.Passport;
import com.yanchun.frombean.RegisterFromBean;
import com.yanchun.user.mq.HelloSender;
import com.yanchun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author quyanchun
 * @Date 2018/12/6
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
     private HelloSender helloSender;
    @ResponseBody
    @RequestMapping("/getPassport")
    public Passport getPassport(@RequestParam("id") Long id) throws Exception{
        try{
            return userService.getPassportById(id);
        }catch (Exception e){

        }
        return null;
    }
    @ResponseBody
    @RequestMapping("/register")
    public void register(@RequestBody RegisterFromBean registerFromBean) throws Exception{
        try{
            userService.registerUser(registerFromBean);
            helloSender.send();
        }catch (Exception e){

        }
    }
}
