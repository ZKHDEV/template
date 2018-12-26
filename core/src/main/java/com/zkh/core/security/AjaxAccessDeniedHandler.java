package com.zkh.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkh.core.model.ResultBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResultBean<?> result = new ResultBean<>(ResultBean.NO_PERMISSION,"未授权");
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}
