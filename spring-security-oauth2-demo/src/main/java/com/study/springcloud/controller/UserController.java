package com.study.springcloud.controller;


import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

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

    /**
     * 在服务器端解析 jwt token
     * @param authentication
     * @return
     */
    @RequestMapping("/getJwt")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) {

        // 解析 jwt token
        String header = request.getHeader("Authorization");
        String token = header.substring(header.lastIndexOf("bearer") + 7);

        return Jwts.parser()
                .setSigningKey("test_secret_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

}
