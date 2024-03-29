package com.study.springcloud.config;

import com.study.springcloud.handler.MyAccessDeniedHandler;
import com.study.springcloud.handler.MyAuthenticationFailureHandler;
import com.study.springcloud.handler.MyAuthenticationSuccessHandler;
import com.study.springcloud.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // dataSource 的声明一定要放在 persistentTokenRepository 的声明之前，否则 dataSource 在使用时还未注入 ！！！
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Bean
    public PasswordEncoder getPw() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单提交
        http.formLogin()
                // 自定义 username 参数名, password 参数名
                .usernameParameter("username123")
                .passwordParameter("password123")
                // 自定义登录页面
                .loginPage("/login.html")
                // 必须和表单提交的接口（位于 login.html 中）一样，回去执行自定义登录逻辑
                .loginProcessingUrl("/login")
                // 登录成功后跳转的页面, POST 请求
                .successForwardUrl("/toMain")
                // 自定义 success handler
//                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
                // 登录失败页面跳转
                .failureForwardUrl("/toError");
                // 自定义 failure handler
//                .failureHandler(new MyAuthenticationFailureHandler("https://www.sina.com.cn"));

        // 授权
        http.authorizeRequests()
                // 放行，不需要认证的页面
                .antMatchers("/login.html").permitAll()
                .antMatchers("/error.html").permitAll()
                .antMatchers("/showLogin").permitAll()

                // 基于权威的权限控制
//                .antMatchers("/main1.html").hasAuthority("admin")
//                .antMatchers("/main1.html").hasAnyAuthority("admin", "admiN")

                // 基于角色的权限控制
//                .antMatchers("/main1.html").hasRole("abc")
//                .antMatchers("/main1.html").hasAnyRole("abc", "abC")

                // 基于 IP 的权限控制
//                .antMatchers("/main1.html").hasIpAddress("127.0.0.1")

                // access 表达式
//                .antMatchers("/main1.html").access("hasIpAddress('127.0.0.1')")

                // 所有请求都需要授权才能访问，需要登录
                .anyRequest().authenticated();

                // 自定义 access 方法的权限控制
//                .anyRequest().access("@myServiceImpl.hasPermission(request, authentication)");

        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler);

        http.rememberMe()
//                .rememberMeParameter()      // 类似 passwordParameter, usernameParameter
                .tokenValiditySeconds(60)     // 超时时间，秒
                // 设置数据源：token 的存储仓库
                .tokenRepository(persistentTokenRepository)
                // 自定义登录逻辑
                .userDetailsService(userDetailsService);

        // 退出登录
        http.logout()
                // 自定义退出接口, 即不使用 spring-security 默认的退出 URL: /logout
                // 一般不要配置这个, 就是用默认的 /logout 作为退出接口即可
//                .logoutUrl("/user/logout")
                .logoutUrl("/logout")
                // 退出登录后跳转的链接, 默认为 /login?logout
                .logoutSuccessUrl("/login.html");

        // Spring Security 4 默认开启 CSRF 防护. 默认拦截请求, 进行 CSRF 处理. CSRF 为了保证不是其他第三方网站访问, 要求访问时携带参数名为 随机值的 token. 如果 token 和服务器端 token 匹配则允许访问. 由于访问第三方网站(恶意网站) 时不会携带该 token 字段, 那么即使恶意网站拿到 cookie 也无法以受害用户的身份访问服务器.
        // 暂时关闭 CSRF 防护
//        http.csrf().disable();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();

        // 设置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        // 自动建表：第一次启动时开启，第二次启动时注释掉
//        jdbcTokenRepository.setCreateTableOnStartup(true);

        return jdbcTokenRepository;
    }
}
