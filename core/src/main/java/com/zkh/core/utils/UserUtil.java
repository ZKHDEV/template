package com.zkh.core.utils;

import com.zkh.core.model.Role;
import com.zkh.core.model.User;
import com.zkh.core.consts.Roles;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * 用户工具类
 */
public class UserUtil {
    /**
     * 获取当前登录用户
     * @return
     */
    public static User getUser() {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前登录用户ID
     * @return
     */
    public static String getUserId() {
        return getUser().getId();
    }

    /**
     * 当前用户是否为管理员
     * @return
     */
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
