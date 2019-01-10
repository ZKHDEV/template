package com.zkh.core.controller;

import com.zkh.core.model.ResultBean;
import com.zkh.core.model.Test;
import com.zkh.core.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private MessageSource messageSource;

    @PostMapping("/test")
    public ResultBean test(@Valid Test test) {
        User user = new User();
        user.setUsername("1111111111111111");
        List<User> users = new ArrayList<>();
        users.add(user);
        test.setUsers(users);
        return new ResultBean<>(test);
    }

    @PostMapping("/user")
    public ResultBean user(@Valid User user) {
        log.info(messageSource.getMessage("test",null,Locale.CHINA));
        return new ResultBean<>(user);
    }
}
