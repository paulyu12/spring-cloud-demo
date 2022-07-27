package com.study.springcloud.service;

import com.study.springcloud.pojo.CommonResult;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@ComponentScan
@FeignClient(value ="MICROSERVICE-PAYMENT")//使用Feign
public interface PaymentService {

    //调用生产者微服务名称为microservice-payment下边的接口
    @GetMapping("/payment/get/{id}")
    CommonResult queryById(@PathVariable("id") Long id);

    //调用生产者微服务名称为microservice-payment下边的模拟超时的接口
    @GetMapping("/payment/feign/timeout")
    String PaymentFeignTimeOut() throws InterruptedException;

}