package com.zkh.core.service;

import com.zkh.core.dao.UserDao;
import com.zkh.core.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserDao userDao;

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
