package com.cyj.oauthcenter.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Description: 用户表
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.oauthcenter.entity
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-03 16:52
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@Slf4j
@Getter
@Setter
/**
 * 自动所有参数的构造方法方法
 */
@AllArgsConstructor
/**
 * 自动无参的构造方法方法
 */
@NoArgsConstructor
/**
 * 建模
 */
@Builder
@Entity
@Table(name = "family_user")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderBy
    @Column(columnDefinition = "bigint(19) unsigned  COMMENT '用户id'")
    private Long userId;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '用户名称'  ")
    private String userName;
    @Column(columnDefinition = "varchar(100) NOT NULL COMMENT '用户密码'  ")
    private String userPassWord;
    @Column(columnDefinition = "datetime COMMENT '用户创建时间' ")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userCreateTime;
    @Column(columnDefinition = "datetime COMMENT '用户最后一次登录时间' ")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userLastLoginTime;
    @Column(columnDefinition = "timestamp COMMENT '最后一次修改时间'", nullable = false, updatable = false, insertable = false)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp userLastUpdateTime;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '创建人'  ")
    private String userFounder;
    @Column(columnDefinition = "varchar(64) COMMENT '修改人'  ")
    private String userUpdateMan;
    @Column(columnDefinition = "bigint(19) unsigned comment '版本信息'  ")
    private Long userVersion;
    @Column(columnDefinition = "int(11) unsigned  COMMENT '是否删除'")
    private Integer userIsDelete;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER) // 指定多对多关系
    @Cascade(value = {CascadeType.ALL}) // 设置级联关系
    @JoinTable(name = "family_user_role", // 指定第三张中间表名称
            joinColumns = {@JoinColumn(name = "user_id")}, // 本表主键userId与第三张中间表user_role_tb的外键user_role_tb_user_id对应
            inverseJoinColumns = {@JoinColumn(name = "role_id")}) // 多对多关系另一张表与第三张中间表表的外键的对应关系
    @NotFound(action = NotFoundAction.IGNORE) // NotFound : 意思是找不到引用的外键数据时忽略，NotFound默认是exception
    private Set<Roles> rolesSet = new HashSet<>();

    @Transient
    private String Pass;

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("小佳");
        user.setUserPassWord("cyj123");
        // 执行加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword().trim());
        log.info("加密后的密码为：{}", encodedPassword);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        // 根据角色控制
        Set<Roles> roles = this.getRolesSet();
        //Set<Permission> permissions=this.getPermissionSet();// 根据权限控制
        for (Roles role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getRoleTrueName()));
        }
        log.info("当前用户拥有的权限为=====>" + auths);
        return auths;
    }

    @Override
    public String getPassword() {
        return this.userPassWord;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    /**
     * 账户是否未过期 指示用户的帐户是否已过期。过期的帐户无法验证。
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定 指示用户是否锁定或解锁。无法对锁定用户进行身份验证。
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示用户的凭据(密码)是否已过期。过期凭据阻止身份验证。
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 指示用户是否启用或禁用。无法对禁用用户进行身份验证。
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassWord='" + userPassWord + '\'' +
                ", userCreateTime=" + userCreateTime +
                ", userLastLoginTime=" + userLastLoginTime +
                ", userLastUpdateTime=" + userLastUpdateTime +
                ", userFounder='" + userFounder + '\'' +
                ", userUpdateMan='" + userUpdateMan + '\'' +
                ", rolesSet=" + rolesSet +
                ", Pass='" + Pass + '\'' +
                '}';
    }

}
