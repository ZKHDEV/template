package com.zkh.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkh.core.model.ResultBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResultBean<?> result = new ResultBean<>(ResultBean.SUCCESS,"登录成功");
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}
