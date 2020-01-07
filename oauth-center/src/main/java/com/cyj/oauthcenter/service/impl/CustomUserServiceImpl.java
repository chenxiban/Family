package com.cyj.oauthcenter.service.impl;

import com.cyj.oauthcenter.dao.UsersRepository;
import com.cyj.oauthcenter.entity.User;
import com.cyj.oauthcenter.utils.IsEmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description: 自定义用户权限认证
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.oauthcenter.service.impl
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-03 16:57
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@Slf4j
@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * 登录时进行的oauth认证
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("收到的账号" + userName);
        User users = usersRepository.findByUserName(userName);
        log.info("用户信息为===>{}",users);
        if (IsEmptyUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("用户名或密码错误！！！");
        }
        log.info("用户为:{};密码为:{}",users.getUsername(),users.getUserPassWord());
        return users;
    }

}
