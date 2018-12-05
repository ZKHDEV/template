package com.zkh.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkh.core.dao.UserDao;
import com.zkh.core.model.Role;
import com.zkh.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDao userDao;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                User user = userDao.findByUsername(s);
                if(user == null) {
                    throw new UsernameNotFoundException(s);
                }
                return user;
            }
        };
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authenticationFilter() {
        return new UsernamePasswordAuthenticationFilter() {
            @Override
            protected String obtainPassword(HttpServletRequest request) {
                String password = null;
                try(InputStream in = request.getInputStream()) {
                    User user = new ObjectMapper().readValue(in,User.class);
                    if (user != null) {
                        password = user.getPassword();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return password;
            }

            @Override
            protected String obtainUsername(HttpServletRequest request) {
                String username = null;
                try(InputStream in = request.getInputStream()) {
                    User user = new ObjectMapper().readValue(in,User.class);
                    if (user != null) {
                        username = user.getUsername();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return username;
            }
        };
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .
                .failureUrl("/login?error")
                .and()
                .logout().permitAll();
        http.csrf().disable();
        //设置自定义用户名密码校验过滤器
        http.addFilterAt(authenticationFilter(),UsernamePasswordAuthenticationFilter.class);
    }


}
