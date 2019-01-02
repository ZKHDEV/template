package com.zkh.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkh.core.model.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public class AjaxUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
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
}
