package com.study.springcloud.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取资源服务器的资源: 用户信息
     * @param authentication
     * @return
     */
    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {

        // 本质就是返回 user information , 也就是我们在 pojo 包中定义的 User 对象
        return authentication.getPrincipal();
    }

}
