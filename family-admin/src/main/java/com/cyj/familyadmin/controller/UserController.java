package com.cyj.familyadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用户控制器
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.familyadmin.controller
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-03 17:59
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用于获取当前用户信息
     *
     * @param authentication
     * @return
     */
    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public Object getCurrentUser(Authentication authentication) {
        log.info("获取到的当前用户信息===>{}", authentication);
        return authentication;
    }

}
