package com.study.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // 根据用户名 s 到数据库查询用户信息，如果不存在则抛出异常
        if (!"admin".equals(s)) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 比较密码，如果匹配成功返回 UserDetails
        String password = passwordEncoder.encode("123");

        return new User(s, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal"));

    }
}
