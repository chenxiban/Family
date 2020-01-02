package com.cyj.gatewaycenter.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Description: 网关限流配置
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.gatewaycenter.config
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-02 17:15
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@Configuration
public class RedisRateLimiterConfig {

    /**
     * 根据请求参数中的username进行限流
     * @return
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("username"));
    }

    /**
     * 根据访问IP进行限流
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

}
