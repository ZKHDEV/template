package com.zkh.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkh.core.model.ResultBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultBean<?> result = new ResultBean<>(ResultBean.FAIL,"登录失败");
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}
