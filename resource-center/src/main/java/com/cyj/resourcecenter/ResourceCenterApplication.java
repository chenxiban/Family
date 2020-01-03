package com.cyj.resourcecenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author ChenYongJia
 * @Description: ResourceCenterApplication 资源中心
 * @ClassName: SpringCloudApplication.java
 * @Date 2020年01月02日 晚上22：54
 * @Email chen87647213@163.com
 */
@Slf4j
@EnableTurbine
@EnableHystrixDashboard
@EnableEurekaClient
@SpringBootApplication
public class ResourceCenterApplication {

    public static void main(String[] args) {
        log.info("=======》启动服务 Resource-center 资源中心项目ing......");
        SpringApplication.run(ResourceCenterApplication.class, args);
        log.info("=======》启动服务 Resource-center 资源中心项目成功......");
    }

}
