package com.cyj.gatewaycenter.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

/**
 * @Description: 后置过滤器
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.gatewaycenter.config
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-02 17:05
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
public class PostGatewayFilterFactory extends AbstractGatewayFilterFactory<PostGatewayFilterFactory.Config> {
    public PostGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                Long startTime = exchange.getAttribute("requestTime");
                long time = System.currentTimeMillis() - startTime;
                System.out.println("接口耗时时间（ms）：" + time);
            }));
        };
    }

    public static class Config {

    }

}
