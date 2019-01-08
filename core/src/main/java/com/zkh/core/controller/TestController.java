package com.zkh.core.controller;

import com.zkh.core.model.ResultBean;
import com.zkh.core.model.Test;
import com.zkh.core.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TestController {
    @PostMapping("/add_user")
    public ResultBean addUser(@Valid Test test) {
        return new ResultBean<>(test);
    }
}
