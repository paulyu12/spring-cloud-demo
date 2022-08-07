# 参考教程
- https://blog.csdn.net/weixin_38007185/article/details/108119724
- https://github.com/ityouknow/spring-cloud-examples
- https://blog.51cto.com/u_14582976/2826683
- https://blog.csdn.net/lydms/article/details/105224210
- https://blog.csdn.net/Mrqiang9001/article/details/121468447
- https://www.bilibili.com/video/BV1Cz4y1k7rd?p=3

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

# spring-security-oauth2
- 授权码模式工程 (spring-security-oauth2-demo)
  - 步骤：
    - 输入用户名密码
    - 到达 oauth2 内置授权页面，点击允许
    - 跳转到 redirect_uri，其中的 code 参数即为授权码
    - 携带授权码获取 token (post 请求)
    - 拿到 token 后，获取资源 /user/getCurrentUser
  - 获取授权码地址：http://127.0.0.1:18080/oauth/authorize?response_type=code&client_id=client-id-1&redirect_uri=https://www.baidu.com&scope=all
  - 携带授权码获取 token 地址：POST http://127.0.0.1:18080/oauth/token
    - 请求时 postman Authorization tab 页填写 client_id 和密码，见 postman-auth-client-username-and-password.png
    - 请求时 postman 表单数据 tab 填写一些授权信息包括授权码，见 postman-oauth2-get-token-form-data.png