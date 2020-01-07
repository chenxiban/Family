package com.cyj.resourcecenter.intercept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 允许任何域名使用 允许任何头 允许任何方法（post、get等）
 *
 * @author ChenYongJia
 * @Description:允许任何域名使用 允许任何头 允许任何方法（post、get等）
 * @ClassName: CorsConfig.java
 * @Date 2018年12月02日 晚上22：54
 * @Email chen87647213@163.com
 */
@Configuration
public class CorsConfig {

    /**
     * 跨域配置
     *
     * @return
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    /**
     * 戈尔过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

}
