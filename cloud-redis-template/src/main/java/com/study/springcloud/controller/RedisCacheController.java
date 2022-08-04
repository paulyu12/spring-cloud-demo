package com.study.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.springcloud.model.Address;
import com.study.springcloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisCacheController {

    @Autowired
    RedisTemplate redisTemplate;

    String cacheKey = "testkey";

    @GetMapping(path = "")
    String cacheTest() {

        if (redisTemplate.hasKey(cacheKey)) {
            long expire = redisTemplate.getExpire(cacheKey);
            return "Key Exists, expire in " + expire;
        }

        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(cacheKey, "123");
        System.out.println(ops.get(cacheKey));

//        JSONObject obj = new JSONObject();
//        obj.put("stringkey", "stringvalue");

        Address address = new Address("No. 5 Street");
        User user = new User("Jack", 20, address);

        ops.set(cacheKey, user);

        User userFromCache = (User) ops.get(cacheKey);

        System.out.println(userFromCache.getName() + " lives at " + userFromCache.getAddress().getAddressName());

        return "OK";
    }

}
