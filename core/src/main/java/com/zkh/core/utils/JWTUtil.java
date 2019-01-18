package com.zkh.core.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtil {
    private static final String SIGNING_KEY = "admin@123";
    private static final Long EXPIRATION = 864000L;

    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();

    }

    public static String getSubjectFromToken(String token) {
        return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
