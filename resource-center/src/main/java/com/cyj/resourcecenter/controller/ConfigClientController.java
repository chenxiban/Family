package com.cyj.resourcecenter.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 配置中心配置控制器
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.configcenter.controller
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-02 11:58
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/config/client")
public class ConfigClientController {
    /** 外部属性 account */
    @Value("${account}")
    private String account;
    /** 外部属性 repositoryUrl*/
    @Value("${repositoryUrl}")
    private String repositoryUrl;

    @GetMapping("/getRepositoryUrl")
    public String getRepositoryUrl() {
        StringBuilder resultUrl = new StringBuilder("Account：");
        resultUrl.append(account)
                .append("<br/>")
                .append("repositoryUrl：")
                .append(repositoryUrl);
        return resultUrl.toString();
    }
}

