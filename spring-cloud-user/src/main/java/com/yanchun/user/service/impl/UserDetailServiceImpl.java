package com.yanchun.user.service.impl;


import com.alibaba.fastjson.JSON;
import com.yanchun.common.dto.PassportDTO;
import com.yanchun.common.entity.Passport;
import com.yanchun.common.model.UserSession;
import com.yanchun.user.config.CustomPasswordEncoder;
import com.yanchun.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sun.font.TrueTypeFont;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        UserSession userSession = new UserSession();
        Passport passport = userRepository.findByPhone(phone);
        BeanUtils.copyProperties(passport, userSession);
        userSession.setAccountNonExpired(true);
        userSession.setAccountNonLocked(true);
        userSession.setCredentialsNonExpired(true);
        userSession.setEnabled(true);
        userSession.setPassword(passwordEncoder.encode(userSession.getPassword()));
        return userSession;
    }
}
