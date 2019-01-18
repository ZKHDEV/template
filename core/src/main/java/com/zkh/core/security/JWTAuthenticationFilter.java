package com.zkh.core.security;

import com.zkh.core.consts.JwtConst;
import com.zkh.core.utils.StringUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(JwtConst.HEADER_TOKEN_KEY);
        if(!StringUtil.isEmpty(token) && token.startsWith(JwtConst.TOKEN_PREFIX)) {
            String subject = Jwts.parser().setSigningKey(JwtConst.SIGNING_KEY).parseClaimsJws(token.replace(JwtConst.TOKEN_PREFIX, "")).getBody().getSubject();
            if(!StringUtil.isEmpty(subject)) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(subject,null, Collections.emptyList()));
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
