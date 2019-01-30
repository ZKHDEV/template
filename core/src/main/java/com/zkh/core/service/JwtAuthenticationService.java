package com.zkh.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zkh.core.model.ResultBean;
import com.zkh.core.model.User;
import com.zkh.core.utils.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Jwt令牌认证
 */
@Service
@Slf4j
public class JwtAuthenticationService {
    private static final Long EXPIRATION = 604800000L;  //有效期为7天
    private static final String SECRET = "Admin@123";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_KEY = "Authorization";

    public static void setAuthentication(HttpServletResponse response, User user) throws IOException {
        String jwt = null;
        try {
            jwt = Jwts.builder()
                    .setSubject(StringUtil.toJsonString(user))
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        if(!StringUtil.isEmpty(jwt)) {
            response.getWriter().println(new ResultBean<>(jwt).toJsonString());
        } else {
            response.getWriter().println(new ResultBean<>(ResultBean.FAIL,"登录失败，请联系系统管理员。").toJsonString());
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_KEY);
        if(StringUtil.isEmpty(token)) {
            return null;
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            User user = StringUtil.fromJsonString(claims.getSubject(),User.class);

            return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
