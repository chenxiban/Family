package com.cyj.oauthcenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;


/**
 * @author ChenYongJia
 * @Description: OauthCenterApplication 权限认证单点登录中心
 * @ClassName: SpringCloudApplication.java
 * @Date 2020年01月02日 晚上22：54
 * @Email chen87647213@163.com
 */
@Slf4j
@EnableDiscoveryClient
@EnableAuthorizationServer
@SpringBootApplication
public class OauthCenterApplication {

    public static void main(String[] args) {
        log.info("=======》启动服务 oauth-center 权限认证单点登录中心项目ing......");
        SpringApplication.run(OauthCenterApplication.class, args);
        log.info("=======》启动服务 oauth-center 权限认证单点登录中心项目成功......");
    }

}
