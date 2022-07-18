package com.study.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//网络通信 一定要实现序列化
//使用lombok  没有配置的童鞋要去百度查一下  jar我们导入了  需要在idea装一个插件就可以了
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Payment implements Serializable {
    private Long id;
    // 微服务 一个服务对应一个数据库，同一个信息可能存在不同的数据库
    private  String serial;
}