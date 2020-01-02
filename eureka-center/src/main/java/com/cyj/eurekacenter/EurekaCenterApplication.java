package com.cyj.eurekacenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ChenYongJia
 * @Description: EurekaCenterApplication 服务注册中心配置
 * @ClassName: SpringCloudApplication.java
 * @Date 2019年12月29日 晚上22：54
 * @Email chen87647213@163.com
 */
@Slf4j
@EnableEurekaServer
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EurekaCenterApplication {

    /**
     * 注册中心启动项
     * @param args
     */
    public static void main(String[] args) {
        log.info("=======》启动服务注册中心 eureka-center 服务项目ing......");
        SpringApplication.run(EurekaCenterApplication.class, args);
        log.info("=======》启动服务注册中心 eureka-center 服务项目成功......");
    }

    /**
     * @author: ChenYongJia
     * @Description: WebSecurityConfig 服务注册中心配置拦截
     * @Date: 2019-12-28 17:17:49
     */
    @EnableWebSecurity
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
        }
    }

}
