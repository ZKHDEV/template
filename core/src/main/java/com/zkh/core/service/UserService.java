package com.zkh.core.service;

import com.zkh.core.bean.User;
import com.zkh.core.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserDao userDao;

    public User findUser(String username) {
        return userDao.findByUsername(username);
    }
}
