package com.cyj.gatewaycenter.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

/**
 * @Description: 前置过滤器
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.gatewaycenter.config
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-02 17:03
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
public class PreGatewayFilterFactory extends AbstractGatewayFilterFactory<PreGatewayFilterFactory.Config> {

    public PreGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put("requestTime", System.currentTimeMillis());
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }

}
