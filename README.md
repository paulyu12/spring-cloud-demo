# 参考教程
- https://blog.csdn.net/weixin_38007185/article/details/108119724
- https://github.com/ityouknow/spring-cloud-examples

# spring-cloud-config
- 配置中心访问
  - http://127.0.0.1:3344/config/dev
- 配置客户端访问
  - http://127.0.0.1:3355/config
  - http://127.0.0.1:3356/config
- 配置动态刷新
  - http://127.0.0.1:3344/actuator/bus-refresh
- 配置局部刷新
  - http://127.0.0.1:3344/actuator/bus-refresh/config-client:3355
  - http://127.0.0.1:3344/actuator/bus-refresh/config-client-3356:3356