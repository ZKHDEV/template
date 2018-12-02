package com.zkh.core.filter;

import com.zkh.core.bean.User;
import com.zkh.core.utils.UserUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "userFilter", urlPatterns = "/*")
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        fillUserInfo((HttpServletRequest) servletRequest);

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            clearAllUserInfo();
        }
    }

    @Override
    public void destroy() {

    }

    private void clearAllUserInfo() {
        UserUtil.clearAllUserInfo();
    }

    private void fillUserInfo(HttpServletRequest request) {
        User user = getUserFromSession(request);

        if (user != null) {
            UserUtil.setUser(user);
        }
    }

    private User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute(UserUtil.KEY_USER);
    }
}
