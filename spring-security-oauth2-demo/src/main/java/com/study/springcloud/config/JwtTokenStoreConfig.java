package com.study.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JwtTokenStoreConfig {

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

        // 设置 jwt secret （也就是 jwt 盐）
        jwtAccessTokenConverter.setSigningKey("test_secret_key");
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore jwtTokenStore() {
        // 将 oauth2 自动生成的 token 转成 jwt token
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

}
