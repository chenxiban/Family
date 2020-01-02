# Spring Cloud Hoxton 版本项目搭建实战
----------------------
## 前言

> 距离Spring Boot 2.2.0的发布已经有一个半月左右时间，由于与之匹配的Spring Cloud版本一直没有Release，所以在这期间碰到的问题都是由于Spring Boot和Spring Cloud版本不匹配导致。

> 很多时候，我们在学习或重建系统的时候都喜欢直接选用最高版本来开发，但是在使用Spring全家桶的时候，这样的选择不一定是最佳选择。主要还是由于Spring全家桶中各项目之间还存在一定的依赖关系。尤其是在大版本迭代期间，是我们尤其要注意的。比如，这次Spring Framework、Spring Boot、Spring Cloud的升级，是一次整体的大版本的升级，涵盖了Spring Framework 5.2、Spring Boot 2.2、Spring Cloud Hoxton`。

> 由于Spring Boot 2.2基于Spring Framework 5.2构建，而Spring Cloud Hoxton又基于Spring Boot 2.2构建。所以，在Spring Cloud Hoxton发布Release版本之前，出现了很多Spring Boot 2.2搭配Spring Cloud Greenwich版本使用而出现各种莫名问题的情况。

> 现在Spring Cloud Hoxton的正式发布，也预示着Spring Cloud用户可以正式开始往Spring Boot 2.2.x版本进行逐步升级。

-----------------------
### 子项目目录

- **eureka-center**
- **admin-center**
- **config-center**
- **gateway-center**
- **resource-center**

-----------------------
### 子项目职责

- **eureka-center** 服务注册与发现
- **admin-center** 监控管理--日志等
- **config-center** 配置中心
- **gateway-center** 网关路由
- **resource-center** 文件资源中心

---------------------------
## 最后

- **更多参考精彩博文请看这里：[陈永佳的博客](https://blog.csdn.net/Mrs_chens)**

- **喜欢博主的小伙伴可以加个关注、点个赞哦，持续更新嘿嘿！**
