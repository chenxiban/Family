package com.cyj.oauthcenter.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYongJia
 * @Description: 角色实体类
 * @ClassName: Roles.java
 * @Date 2018年12月04日 下午20:40:56
 * @Email 867647213@qq.com
 */
@SuppressWarnings("serial")
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
@Table(name = "family_roles")
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderBy
    @Column(columnDefinition = "bigint(19) unsigned  COMMENT '角色id'")
    private Long roleId;
    @Column(columnDefinition = "varchar(100) NOT NULL COMMENT '角色名称'  ")
    private String roleName;
    @Column(columnDefinition = "varchar(50) COMMENT '角色权限名'  ")
    private String roleTrueName;
    @Column(columnDefinition = "char(1) COMMENT '角色类型'  ")
    private String roleType;
    @Column(columnDefinition = "char(1) DEFAULT '0' COMMENT '角色状态(0:启用,1:禁用)'  ")
    private String roleStatus;
    @Column(columnDefinition = "int NOT NULL COMMENT '角色权重'  ")
    private Integer roleWeight;
    @Column(columnDefinition = "varchar(1000) COMMENT '角色备注描述'  ")
    private String roleRemark;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "datetime COMMENT '创建时间' ")
    private Date roleCreateTime;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '创建人'  ")
    private String roleCreateMan;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "timestamp COMMENT '最后一次修改时间'", nullable = false, updatable = false, insertable = false)
    private Timestamp roleUpdateTime;
    @Column(columnDefinition = "varchar(64) COMMENT '修改人'  ")
    private String roleUpdateMan;
    @Column(columnDefinition = "bigint(19) unsigned comment '版本信息'  ")
    private Long roleVersion;
    @Column(columnDefinition = "int(11) unsigned  COMMENT '是否删除'")
    private Integer roleIsDelete;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER) // 多对多关系
    @Cascade(value = {CascadeType.ALL}) // 级联关系
    @JoinTable(name = "family_user_role", // 指定第三张中间表名称
            joinColumns = {@JoinColumn(name = "role_id")}, // 本表主键roleId与第三张中间表user_role_tb的外键user_role_tb_role_id对应.本表与中间表的外键对应关系
            inverseJoinColumns = {@JoinColumn(name = "user_id")}) // 另一张表与中间表的外键的对应关系
    @NotFound(action = NotFoundAction.IGNORE) // NotFound : 意思是找不到引用的外键数据时忽略，NotFound默认是exception
    private Set<User> usersSet = new HashSet<>();

    // 关联权限
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
    // 指定多对多关系 //默认懒加载,只有调用getter方法时才加载数据
    @JoinTable(name = "family_role_permission", // 指定第三张中间表名称
            joinColumns = {@JoinColumn(name = "role_id")}, // 本表主键userId与第三张中间表user_role_tb的外键user_role_tb_user_id对应
            inverseJoinColumns = {@JoinColumn(name = "permission_id")}) // 多对多关系另一张表与第三张中间表表的外键的对应关系
    @NotFound(action = NotFoundAction.IGNORE) // NotFound : 意思是找不到引用的外键数据时忽略，NotFound默认是exception
    private Set<Permission> roleSet = new HashSet<>();

}
