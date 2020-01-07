package com.cyj.resourcecenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 配置中心配置控制器
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.configcenter.controller
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-02 11:58
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@Slf4j
@Configuration
@RefreshScope // 用于刷新配置
@RestController
public class ConfigClientController {

    @Resource
    @Value("${config.info}")
    private String configInfo;

    /**
     * http://localhost:4010/
     * @return
     */
    @GetMapping("/configInfo")
    public String getConfigInfo() {
        log.info("configInfo=================>"+configInfo);
        return configInfo;
    }

}

