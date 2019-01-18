package com.zkh.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkh.core.consts.JwtConst;
import com.zkh.core.utils.JWTUtil;
import com.zkh.core.utils.StringUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final Long EXPIRATION = 864000L;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    public JWTLoginFilter() {
    }

    public JWTLoginFilter(AuthenticationManager authManager) {
        setAuthenticationManager(authManager);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Object obj = authResult.getPrincipal();
        if(obj != null) {
            UserDetails userDetails = (UserDetails)obj;
            String token = Jwts.builder()
                    .setSubject(StringUtil.toJsonString(userDetails))
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                    .signWith(SignatureAlgorithm.HS512, JwtConst.SIGNING_KEY)
                    .compact();
            response.addHeader(JwtConst.HEADER_TOKEN_KEY,JwtConst.TOKEN_PREFIX + token);
        }

        successHandler.onAuthenticationSuccess(request,response,authResult);
    }
}
