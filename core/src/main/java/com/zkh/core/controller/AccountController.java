package com.zkh.core.controller;

import com.zkh.core.model.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @RequestMapping("/login_page")
    public ResultBean loginPage() {
        return new ResultBean<>(ResultBean.FAIL,"登录失败");
    }
}
