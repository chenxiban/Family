package com.cyj.oauthcenter.config;

import com.cyj.oauthcenter.service.impl.CustomUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @Description: 授权认证服务中心配置
 * @ClassName: AuthorizationServerConfig.java
 * @author ChenYongJia
 * @Date 2018年12月04日 下午20:40:56
 * @Email 867647213@qq.com
 */

/**
 * 授权认证服务中心配置
 */
@Slf4j
@Configuration
/**
 * 启用授权认证中心服务
 */
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final int ACCESSTOKENVALIDITYSECONDS = 7200 * 12 * 7;
    private static final int REFRESHTOKENVALIDITYSECONDS = 7200 * 12 * 7;
    @Autowired
    private CustomUserServiceImpl userDetailsService;

    /**
     * configure(ClientDetailsServiceConfigurer clients)配置 appid、appkey 、回调地址、token有效期
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息；
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("client_1").secret(passwordEncoder().encode("123456"))
                // 授权码授权模式下的回调地址
                //.redirectUris("https://blog.csdn.net/Mrs_chens")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token").scopes("all")
                .accessTokenValiditySeconds(ACCESSTOKENVALIDITYSECONDS)
                // 单点登录时配置
                .redirectUris("http://localhost:3012/login")
                // 如果需要跳过授权操作进行自动授权可以添加autoApprove(true)配置：
                // 自动授权配置
                .autoApprove(true)
                // 授权类型
                .refreshTokenValiditySeconds(REFRESHTOKENVALIDITYSECONDS);
    }

    /**
     * configure(AuthorizationServerEndpointsConfigurer endpoints)
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token
     * services)，还有token的存储方式(tokenStore)；
     */
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager()).allowedTokenEndpointRequestMethods(HttpMethod.GET,
                HttpMethod.POST, HttpMethod.PUT,
                HttpMethod.DELETE);
        // 必须加上他，不然刷新令牌接口会报错
        endpoints.authenticationManager(authenticationManager());
        endpoints.userDetailsService(userDetailsService);
    }

    /**
     * onfigure(AuthorizationServerSecurityConfigurer security) 用来配置令牌端点(Token
     * Endpoint)的安全约束；
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        // 获取密钥需要身份认证，使用单点登录时必须配置
        oauthServer.tokenKeyAccess("isAuthenticated()");
        // 允许表单认证
        oauthServer.allowFormAuthenticationForClients();
        // 允许check_token访问
        oauthServer.checkTokenAccess("permitAll()");
    }

    /**
     * 用来做验证
     *
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        AuthenticationManager authenticationManager = new AuthenticationManager() {
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return daoAuhthenticationProvider().authenticate(authentication);
            }
        };
        return authenticationManager;
    }

    @Bean
    public AuthenticationProvider daoAuhthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // 加密方式
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

}
