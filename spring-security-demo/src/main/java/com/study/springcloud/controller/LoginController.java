package com.study.springcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    /**
     * 登录
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "redirect:main.html";
    }

}
