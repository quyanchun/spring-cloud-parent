package com.yanchun.user.service.impl;


import com.yanchun.common.model.UserSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 根据用户名获取用户<br>
 * <p>
 * 密码校验请看下面两个类
 *
 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
 * @see org.springframework.security.authentication.dao.DaoAuthenticationProvider
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

        // TODO 根据用户名，查找到对应的密码，与权限
        String password = passwordEncoder.encode("11111111");
        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        UserSession userSession = new UserSession();
        userSession.setPhone(phone);
        userSession.setPhone(password);
        return userSession;
    }
}
