package com.cyj.oauthcenter.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Chenyongjia
 * @Description: 权限实体类
 * @Date 2018-11-13 下午5:42:22
 * @Email chen867647213@163.com
 */
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
@Table(name = "family_permission")
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderBy
    @Column(columnDefinition = "bigint(19) unsigned comment '权限ID'  ")
    private Long permissionId;
    @Column(columnDefinition = "varchar(50) NOT NULL comment '权限所属模块'  ")
    private String permissionModule;
    @Column(columnDefinition = "varchar(50) NOT NULL comment '权限名称'  ")
    private String permissionName;
    @Column(columnDefinition = "varchar(50) comment '权限资源对象'  ")
    private String permissionValue;
    @Column(columnDefinition = "varchar(60) comment '权限资源路径'  ")
    private String permissionUrl;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "datetime COMMENT '创建时间'")
    private Date permissionCreateTime;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "timestamp COMMENT '最后一次修改时间'", nullable = false, updatable = false, insertable = false)
    private Timestamp permissionLastUpdateTime;
    @Column(columnDefinition = "bigint(19) unsigned comment '版本信息'  ")
    private Long permissionVersion;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //指定多对多关系    //默认懒加载,只有调用getter方法时才加载数据
    @JoinTable(name = "family_role_permission",                       //指定第三张中间表名称
            joinColumns = {@JoinColumn(name = "permission_id")}, //本表主键userId与第三张中间表user_role_tb的外键user_role_tb_user_id对应
            inverseJoinColumns = {@JoinColumn(name = "role_id")})  //多对多关系另一张表与第三张中间表表的外键的对应关系
    @NotFound(action = NotFoundAction.IGNORE)    //NotFound : 意思是找不到引用的外键数据时忽略，NotFound默认是exception
    private Set<Roles> roleSet = new HashSet<>();
    @Transient
    private Long id;
    @Transient
    private String label;
    @Transient
    private List<Permission> children;

    public Permission(String permissionValue, String permissionModule,
                      String permissionName) {
        super();
        this.permissionValue = permissionValue;
        this.permissionModule = permissionModule;
        this.permissionName = permissionName;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionModule='" + permissionModule + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", permissionValue='" + permissionValue + '\'' +
                ", permissionUrl='" + permissionUrl + '\'' +
                ", permissionCreateTime=" + permissionCreateTime +
                ", permissionLastUpdateTime=" + permissionLastUpdateTime +
                ", children=" + children +
                '}';
    }

}
