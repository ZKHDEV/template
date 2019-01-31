package com.zkh.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zkh.core.model.ResultBean;
import com.zkh.core.model.User;
import com.zkh.core.security.JWTAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AccountController {
    /**
     * 刷新令牌
     * @param request
     * @return
     */
    @RequestMapping("/refresh")
    public ResultBean refreshToken(HttpServletRequest request) {
        User user = JWTAuthenticationService.getUser(request);
        if (user == null) {
            return new ResultBean<>(ResultBean.NO_LOGIN,"认证失败");
        } else {
            try {
                String token = JWTAuthenticationService.buildJwt(user);
                return new ResultBean<>(token);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
                return new ResultBean<>(ResultBean.FAIL,"认证失败，请联系系统管理员。");
            }
        }
    }
}
