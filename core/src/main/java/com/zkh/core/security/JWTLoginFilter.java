package com.zkh.core.security;

import com.zkh.core.model.ResultBean;
import com.zkh.core.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录过滤器
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        setAuthenticationManager(authManager);
        setFilterProcessesUrl(url);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JWTAuthenticationService.setJwt(response, (User) authResult.getPrincipal());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(new ResultBean<>(ResultBean.FAIL,"用户名或密码错误").toJsonString());
    }
}
