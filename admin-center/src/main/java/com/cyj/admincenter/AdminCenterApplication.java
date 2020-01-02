package com.cyj.admincenter;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 *
 * @author Chenyongjia
 * @Description: AdminCenterApplication
 * @ClassName: Application.java
 * @Date 2019年12月30日 晚上20:29:06
 * @Email 867647213@qq.com
 */
@Slf4j
@EnableAdminServer
@EnableEurekaClient
@SpringBootApplication
public class AdminCenterApplication {

    public static void main(String[] args) {
        log.info("=======》启动 Admin 管理项目ing......");
        SpringApplication.run(AdminCenterApplication.class, args);
        log.info("=======》启动 Admin 管理项目成功......");
    }

}
