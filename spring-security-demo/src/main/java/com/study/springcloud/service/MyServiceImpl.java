package com.study.springcloud.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Service
public class MyServiceImpl implements MyService {
    @Override
    public boolean hasPermission(HttpServletRequest httpServletRequest, Authentication authentication) {

        /**
         * 本函数本质自定义访问控制逻辑
         * 传入的参数中 httpServletRequest 是来自用户的请求，authentication 是 loadUserDetailsService 从数据库中拿到的用户信息中的 authentication
         * 判断逻辑：
         * - 从 httpServletRequest 中获取 uri
         * - 从用户信息中获取 authentication
         * - 判断 authentication 中是否包含 uri，如果包含，那么允许访问该页面
         */

        // 获取主体
        Object obj = authentication.getPrincipal();

        // 判断主体是否属于 UserDetails
        if (obj instanceof UserDetails) {
            UserDetails obj1 = (UserDetails) obj;

            Collection<? extends GrantedAuthority> authorities = obj1.getAuthorities();

            // 判断请求的 URI 是否在请求的权威中
            return authorities.contains(new SimpleGrantedAuthority(httpServletRequest.getRequestURI()));

        }

        return false;
    }
}
