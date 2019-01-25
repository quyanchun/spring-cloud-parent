package com.yanchun.user.config;

import com.yanchun.common.utils.MD5;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {
 
    @Override
    public String encode(CharSequence charSequence) {
        String pwd = charSequence.toString();
        String md5Pwd = MD5.encode(pwd);
        return md5Pwd;
    }
 
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String pwd = charSequence.toString();
        String md5Pwd = MD5.encode(pwd);
        if(md5Pwd.equals(s)){
            System.out.println("pass");
            return true;
        }
        throw new DisabledException("--密码错误--");
    }
}

