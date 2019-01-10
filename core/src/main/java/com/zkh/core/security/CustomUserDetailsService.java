package com.zkh.core.security;

import com.zkh.core.model.User;
import com.zkh.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 自定义用户信息获取业务
 */
@Component("customUserDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("=== >>>> load user: {}", s);
        User user = userService.findByUsername(s);
        if(user == null) {
            log.error(" --- user not found. userid: {}", s);
            throw new UsernameNotFoundException(String.format("userid: %s",s));
        }

        log.info("  |--- user found. name: {}", user.getNick());
        return user;
    }
}
