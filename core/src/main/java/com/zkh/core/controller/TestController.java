package com.zkh.core.controller;

import com.zkh.core.model.ResultBean;
import com.zkh.core.model.User;
import com.zkh.core.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/test")
    public ResultBean test() {
        User user = UserUtil.getUser();
        return new ResultBean<>(user);
    }

    @PostMapping("/user")
    public ResultBean user(@Valid User user) {
        log.info(messageSource.getMessage("test",null,Locale.CHINA));
        return new ResultBean<>(user);
    }
}
