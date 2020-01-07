package com.cyj.resourcecenter.utils;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Mashuai
 * @Description: 权限实体类
 * @Date 2018-5-10 下午5:46:22
 * @Email 1119616605@qq.com
 * <p>
 * CREATE TABLE `permissiontb` (
 * `permissionId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限ID',
 * `permissionName` varchar(50) NOT NULL COMMENT '权限名称',
 * `permissionValue` varchar(50) DEFAULT NULL COMMENT '权限资源对象',
 * `permissionLastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '权限最近一次修改时间',
 * PRIMARY KEY (`permissionId`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8
 */
@Data
public class SysPermission {

    private Integer permission_id;
    private String permission_value;
    private String permission_module;
    private String permission_name;
    private Timestamp permission_last_update_time;

    public SysPermission(String permission_value, String permission_module,
                         String permission_name) {
        super();
        this.permission_value = permission_value;
        this.permission_module = permission_module;
        this.permission_name = permission_name;
    }

}
