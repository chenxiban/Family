/*
package com.cyj.gatewaycenter.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

*/
/**
 * @Description: 路由配置
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.gatewaycenter.config
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-02 16:30
 * @Email: chen87647213@163.com
 * @Version: 1.0
 *//*

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route2", r -> r.path("/user/getByUsername")
                        .uri("http://localhost:5010/user/getByUsername"))
                .build();
    }

}
*/
