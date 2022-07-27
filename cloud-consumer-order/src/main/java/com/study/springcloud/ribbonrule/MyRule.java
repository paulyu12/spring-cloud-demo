package com.study.springcloud.ribbonrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//自定义负载均衡路由规则类
@Configuration
public class MyRule {
    @Bean
    public IRule customRule() {
        // 定义为随机
        return new RoundRobinRule();
    }
}