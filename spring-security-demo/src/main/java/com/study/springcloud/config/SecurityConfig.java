package com.study.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPw() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单提交
        http.formLogin()
                // 自定义登录页面
                .loginPage("/login.html")
                // 必须和表单提交的接口（位于 login.html 中）一样，回去执行自定义登录逻辑
                .loginProcessingUrl("/login")
                // 登录成功后跳转的页面, POST 请求
                .successForwardUrl("/toMain")
                // 登录失败页面跳转
                .failureForwardUrl("/toError");

        // 授权
        http.authorizeRequests()
                // 放行，不需要认证的页面
                .antMatchers("/login.html").permitAll()
                .antMatchers("/error.html").permitAll()
                // 所有请求都需要授权才能访问，需要登录
                .anyRequest().authenticated();

        // 暂时关闭 CSRF 防护
        http.csrf().disable();
    }
}
