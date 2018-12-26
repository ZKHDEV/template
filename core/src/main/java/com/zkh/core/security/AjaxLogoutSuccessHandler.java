package com.zkh.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkh.core.model.ResultBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResultBean<?> result = new ResultBean<>(ResultBean.SUCCESS,"注销成功");
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}
