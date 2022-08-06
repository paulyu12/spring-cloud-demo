package com.study.springcloud.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

//    /**
//     * 登录
//     * @return
//     */
//    @RequestMapping("/login")
//    public String login() {
//        return "redirect:main.html";
//    }

    /**
     * 登录
     * @return
     */
    // 基于注解的访问控制。（这是一个基于角色的访问控制，相当于 SecurityConfig 中的 hasRoles）
    // 访问这个接口时，如果 SecurityConfig 中有基于配置的控制，则先走登录；如果登录后还是没有权限，则调用该接口报 500
    @Secured("ROLE_abc")
    @RequestMapping("/toMain")
    public String toMain() {
        return "redirect:main.html";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping("/toError")
    public String toError() {
        return "redirect:error.html";
    }

}
