package com.study.springcloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                
                // 默认全部 url 进行拦截，都需要认证
                .anyRequest().authenticated()
                .and()

                // 对资源服务器的资源 URL 进行匹配
                .requestMatchers()
                .antMatchers("/user/**");
    }
}
