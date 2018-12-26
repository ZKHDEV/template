package com.zkh.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkh.core.model.ResultBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultBean<?> result = new ResultBean<>(ResultBean.NO_LOGIN,"未登录");
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}
