package com.zkh.core.utils;

import com.zkh.core.model.Role;
import com.zkh.core.model.User;
import com.zkh.core.consts.Roles;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class UserUtil {
    public static User getUser() {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getUserId() {
        return getUser().getId();
    }

    public static boolean isAdmin() {
        List<Role> roles = getUser().getRoles();

        for (Role role : roles) {
            if (Roles.ADMIN .equals(role.getName())) {
                return true;
            }
        }

        return false;
    }
}
