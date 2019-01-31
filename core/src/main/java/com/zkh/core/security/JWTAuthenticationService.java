package com.zkh.core.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zkh.core.model.ResultBean;
import com.zkh.core.model.User;
import com.zkh.core.utils.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
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
public class JWTAuthenticationService {
    //Token有效期
    private static final Long EXPIRATION = 604800000L;  //有效期为7天
    //加密秘钥
    private static final String SECRET = "Admin@123";
    //Token前缀
    private static final String TOKEN_PREFIX = "Bearer ";
    //请求头字段
    private static final String HEADER_KEY = "Authorization";

    /**
     * 构建JWT
     * @param obj
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> String buildJwt(T obj) throws JsonProcessingException {
        return Jwts.builder()
                    .setSubject(StringUtil.toJsonString(obj))
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
    }

    /**
     * 解析Token获取JWT主题
     * @param token
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T parseJwtSubject(String token, Class<T> clazz) throws IOException {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
        return StringUtil.fromJsonString(claims.getSubject(), clazz);
    }

    /**
     * 设置JWT响应
     * @param response
     * @param user
     * @throws IOException
     */
    public static void setJwt(HttpServletResponse response, User user) throws IOException {
        String jwt = null;
        try {
            jwt = buildJwt(user);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        if(!StringUtil.isEmpty(jwt)) {
            response.getWriter().println(new ResultBean<>(jwt).toJsonString());
        } else {
            response.getWriter().println(new ResultBean<>(ResultBean.FAIL,"认证失败，请联系系统管理员。").toJsonString());
        }
    }

    /**
     * 获取请求头用户信息
     * @param request
     * @return
     */
    public static User getUser(HttpServletRequest request) {
        String token = request.getHeader(HEADER_KEY);
        if(!StringUtil.isEmpty(token)) {
            try {
                return parseJwtSubject(token, User.class);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }
}
