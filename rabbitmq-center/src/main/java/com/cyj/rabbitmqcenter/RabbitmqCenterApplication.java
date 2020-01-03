package com.cyj.rabbitmqcenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ChenYongJia
 * @Description: RabbitmqCenterApplication 消息中心
 * @ClassName: SpringCloudApplication.java
 * @Date 2020年01月02日 晚上22：54
 * @Email chen87647213@163.com
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class RabbitmqCenterApplication {

    public static void main(String[] args) {
        log.info("=======》启动服务 rabbitmq-center 消息中心项目ing......");
        SpringApplication.run(RabbitmqCenterApplication.class, args);
        log.info("=======》启动服务 rabbitmq-center 消息中心项目成功......");
    }

}
