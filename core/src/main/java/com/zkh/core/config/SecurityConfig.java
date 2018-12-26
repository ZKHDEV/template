package com.zkh.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        //设置自定义用户名密码校验过滤器
        http.addFilterAt(usernamePasswordAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }


}
