package com.cyj.oauthcenter.config;

import com.cyj.oauthcenter.handler.MyAuthenticationFailureHandler;
import com.cyj.oauthcenter.handler.MyAuthenticationSuccessHandler;
import com.cyj.oauthcenter.service.impl.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ChenYongJia
 * @Description: 安全配置文件： 启用方法级的权限认证
 * @ClassName: SpringSecurityConfig.java
 * @Date 2018年12月04日 下午20:40:56
 * @Email 867647213@qq.com
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserServiceImpl userDetailsService;

    /**
     * 认证成功
     */
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    /**
     * 认证失败
     */
    @Autowired
    private MyAuthenticationFailureHandler failHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 拦截所有请求,使用httpBasic方式登陆
        // 所有请求都需要SpringSecurity认证授权
        http.authorizeRequests()
                .antMatchers("/resources/**")
                .permitAll().antMatchers("/login", "/oauth/**", "logout/**").permitAll()
                .antMatchers("/**").fullyAuthenticated()
                .and().csrf().disable().formLogin()
                .and().headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favor.ioc");
    }

    /*
     * OK ，关于这个配置我要多说两句：
     *
     * 1.首先当我们要自定义Spring Security的时候我们需要继承自WebSecurityConfigurerAdapter来完成，相关配置重写对应
     * 方法即可。 2.我们在这里注册CustomUserService的Bean，然后通过重写configure方法添加我们自定义的认证方式。
     * 3.在configure(HttpSecurity
     * http)方法中，我们设置了登录页面，而且登录页面任何人都可以访问，然后设置了登录失败地址，也设置了注销请求，注销请求也是任何人都可以访问的。
     * 4.permitAll表示该请求任何人都可以访问，.anyRequest().authenticated(),表示其他的请求都必须要有权限认证。
     * 5.这里我们可以通过匹配器来匹配路径，比如antMatchers方法，假设我要管理员才可以访问admin文件夹下的内容，我可以这样来写：.
     * antMatchers("/admin/**").hasRole("ROLE_ADMIN")，也可以设置admin文件夹下的文件可以有多个角色来访问，
     * 写法如下：.antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")
     * 6.可以通过hasIpAddress来指定某一个ip可以访问该资源,假设只允许访问ip为210.210.210.210的请求获取admin下的资源，
     * 写法如下.antMatchers("/admin/**").hasIpAddress("210.210.210.210")
     * 7.更多的权限控制方式参看源码：
     */

}
