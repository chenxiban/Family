package com.cyj.gatewaycenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 过滤器配置
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.gatewaycenter.config
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-02 17:05
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@Configuration
public class FilterConfig {

    /**
     * 注入前置过滤器
     * @return
     */
    @Bean
    public PreGatewayFilterFactory preGatewayFilterFactory() {
        return new PreGatewayFilterFactory();
    }

    /**
     * 注入后置过滤器
     * @return
     */
    @Bean
    public PostGatewayFilterFactory postGatewayFilterFactory() {
        return new PostGatewayFilterFactory();
    }

}
