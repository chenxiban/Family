package com.cyj.resourcecenter;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ChenYongJia
 * @Description: ResourceCenterApplication 资源中心
 * @ClassName: SpringCloudApplication.java
 * @Date 2020年01月02日 晚上22：54
 * @Email chen87647213@163.com
 */
@Slf4j
@EnableFeignClients
@MapperScan("com.cyj.resourcecenter.dao")
@EnableEurekaClient
@SpringBootApplication
public class ResourceCenterApplication {

    public static void main(String[] args) {
        log.info("=======》启动服务 Resource-center 资源中心项目ing......");
        SpringApplication.run(ResourceCenterApplication.class, args);
        log.info("=======》启动服务 Resource-center 资源中心项目成功......");
    }

}
