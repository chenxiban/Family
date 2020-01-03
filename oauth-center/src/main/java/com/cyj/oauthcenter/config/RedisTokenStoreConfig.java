/*package com.cyj.oauthcenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

*//**
 * @Description: 添加在Redis中存储令牌的配置
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.oauthcenter.config
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-03 17:37
 * @Email: chen87647213@163.com
 * @Version: 1.0
 *//*
@Configuration
public class RedisTokenStoreConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

}*/

