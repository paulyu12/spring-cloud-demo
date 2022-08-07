package com.study.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

//    @Autowired
//    @Qualifier("redisTokenStore")
//    private TokenStore tokenStore;

    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    // 密码模式配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // 设置 jwt token 增强内容
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(delegates);

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)

                // 使用 redis 保存用户 token
//                .tokenStore(tokenStore);

                // oauth2 默认的 access token 转成 jwt tokenStore
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)

                .tokenEnhancer(tokenEnhancerChain);
    }

    // 授权服务器配置
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // 这里的 clients 指的是我们的应用服务器, 这里设置一些我们的应用服务器的信息
        // 正常来说这些信息应该是 clients 在首次向授权服务器注册的时候, 才会拿到的这些信息, 然后带着这些信息向授权服务器请求授权码
        // 这里为了方便, 直接将这些 client 信息写在内存中, 直接携带这些信息向授权服务器请求授权码
        clients.inMemory()
                // 客户端 ID
                .withClient("client-id-1")

                // 密钥
                .secret( passwordEncoder.encode("112233") )

                // 重定向地址
                .redirectUris("https://www.baidu.com")

                // 授权范围
                .scopes("all")

                /**
                 * 授权类型
                 * authorization_code: 授权码模式
                 */
                .authorizedGrantTypes("authorization_code", "password");

    }
}
