package com.zkh.core.utils;

import com.zkh.core.bean.Role;
import com.zkh.core.bean.User;
import com.zkh.core.consts.Roles;
import com.zkh.core.exception.UnloginException;
import org.slf4j.MDC;

import java.util.List;

public class UserUtil {
    public static final String KEY_USER = "user";

    private final static ThreadLocal<User> tlUser = new ThreadLocal<User>();

    public static void setUser(User user) {
        tlUser.set(user);
        MDC.put(KEY_USER, user.getUsername());
    }

    public static User getUserIfLogin() {
        return tlUser.get();
    }

    public static User getUser() {
        User user = tlUser.get();

        if (user == null) {
            throw new UnloginException();
        }

        return user;
    }

    public static String getUserId() {
        return getUser().getId();
    }

    public static void clearAllUserInfo() {
        tlUser.remove();

        MDC.remove(KEY_USER);
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
