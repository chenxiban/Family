package com.cyj.consulcenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ChenYongJia
 * @Description: ConsulCenterApplication 服务注册中心和配置中心项目
 * @ClassName: SpringCloudApplication.java
 * @Date 2019年12月29日 晚上22：54
 * @Email chen87647213@163.com
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulCenterApplication {

    public static void main(String[] args) {
        log.info("启动 consul-center 服务注册中心和配置中心项目...");
        SpringApplication.run(ConsulCenterApplication.class, args);
        log.info("启动 consul-center 服务注册中心和配置中心项目成功...");
    }

}
