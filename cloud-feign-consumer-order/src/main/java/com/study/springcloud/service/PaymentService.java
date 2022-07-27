package com.study.springcloud.service;

import com.study.springcloud.pojo.CommonResult;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@ComponentScan
@FeignClient(value ="MICROSERVICE-PAYMENT")//使用Feign
public interface PaymentService {

    @GetMapping("/payment/get/{id}")
    CommonResult queryById(@PathVariable("id") Long id);

}