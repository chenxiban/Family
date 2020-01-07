package com.cyj.consulclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 创建ConfigClientController，从Consul配置中心中获取配置信息
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.consulclient.controller
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-07 15:36
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@Slf4j
@Configuration
@RefreshScope
@RestController
public class ConfigClientController {

    @Resource
    @Value("${config.info}")
    private String configInfo;

    /**
     * http://localhost:8890/configInfo
     * @return
     */
    @GetMapping("/configInfo")
    public String getConfigInfo() {
        log.info("configInfo=================>"+configInfo);
        return configInfo;
    }

}
