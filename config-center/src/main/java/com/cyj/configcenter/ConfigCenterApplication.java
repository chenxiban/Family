package com.cyj.configcenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * 启动类
 *
 * @author Chenyongjia
 * @Description: ConfigCenterApplication
 * @ClassName: Application.java
 * @Date 2020年01月02日 晚上7:29:06
 * @Email 867647213@qq.com
 */
@Slf4j
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class ConfigCenterApplication {

    public static void main(String[] args) {
        log.info("=======》启动 config-center 配置中心项目ing......");
        SpringApplication.run(ConfigCenterApplication.class, args);
        log.info("=======》启动 config-center 配置中心成功......");
    }

}
