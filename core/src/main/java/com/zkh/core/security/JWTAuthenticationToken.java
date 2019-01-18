package com.zkh.core.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JWTAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public JWTAuthenticationToken(Object principal) {
        super(principal, null, Collections.emptyList());
    }
}
