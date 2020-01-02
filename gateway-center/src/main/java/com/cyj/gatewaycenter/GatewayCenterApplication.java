package com.cyj.gatewaycenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 *
 * @author Chenyongjia
 * @Description: GatewayCenterApplication
 * @ClassName: Application.java
 * @Date 2020年01月02日 晚上20:29:06
 * @Email 867647213@qq.com
 */
@Slf4j
@EnableEurekaClient
@SpringBootApplication
public class GatewayCenterApplication {

    public static void main(String[] args) {
        log.info("=======》启动 gateway-center 网关路由项目ing......");
        SpringApplication.run(GatewayCenterApplication.class, args);
        log.info("=======》启动 gateway-center 网关路由项目ing......");
    }

}
