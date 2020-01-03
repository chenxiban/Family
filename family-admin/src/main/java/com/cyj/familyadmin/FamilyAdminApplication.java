package com.cyj.familyadmin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ChenYongJia
 * @Description: 家庭生态圈启动项
 * @ClassName: FamilyAdminApplication.java
 * @Date 2020-1-3 17:55:54
 * @Email 867647213@qq.com
 */
@Slf4j
@EnableOAuth2Sso
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FamilyAdminApplication {

    public static void main(String[] args) {
        log.info("=======》启动服务 family-admin 家庭生态圈后台管理项目ing......");
        SpringApplication.run(FamilyAdminApplication.class, args);
        log.info("=======》启动服务 family-admin 家庭生态圈后台管理项目成功......");
    }

}
