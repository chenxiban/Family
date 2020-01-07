package com.cyj.resourcecenter.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @Description: 权限实体类
 * @author Chenyongjia
 * @Date 2018-11-13 下午5:42:22
 * @Email chen867647213@163.com
 * 
 */
@Getter
@Setter
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@ToString
public class Permission implements Serializable {

	private Integer permissionId;// 权限ID
	private String permissionValue;// 权限
	private String permissionModule;// 权限所属模块
	private String permissionName;// 权限备注说明介绍
	private Timestamp permissionLastUpdateTime;// 权限修改日期时间
	
	public Permission(String permissionValue, String permissionModule,
			String permissionName) {
		super();
		this.permissionValue = permissionValue;
		this.permissionModule = permissionModule;
		this.permissionName = permissionName;
	}
	
}
