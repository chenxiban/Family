/*
package com.cyj.resourcecenter.controller;

import java.util.Collection;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

*/
/**
 * 
 * @Description:   权限控制器 
 * @ClassName:     PermissionController.java
 * @author         ChenYongJia
 * @Date           2018年12月04日 下午20:40:56
 * @Email          867647213@qq.com
 *//*

@Slf4j
@RestController
@RequestMapping(value = "/permission", name = "权限模块")
public class PermissionController {

	*/
/**
	 * springmvc在启动时候将所有贴有请求映射标签：RequestMapper方法收集起来封装到该对象中
	 * SpringMVC所有控制器中的请求映射集合
	 *//*

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	*/
/**
	 * http://localhost:4010/permission/updatePermission 更新系统权限信息
	 * 
	 * @author ChenYongJia
	 *//*

	//@PreAuthorize(value = "hasAuthority('permission:updatePermission')")
	@RequestMapping(value = "/updatePermission", name = "更新系统权限",method=RequestMethod.POST)
	public Object updatePermission() {
		return this.updateSysPermission();
	}
	
	*/
/**
	 * 收集系统中所有权限数据更新到数据库
	 * 
	 * * @author ChenYongJia
	 *//*

	public List<Permission> updateSysPermission() {

		Map<RequestMappingInfo, HandlerMethod> requestMap = handlerMapping.getHandlerMethods();// SpringMVC所有控制器中的请求映射集合
		Collection<HandlerMethod> handlerMethods = requestMap.values();// 获取所有controller中所有带有@RequestMapper注解的方法
		if (handlerMethods == null || handlerMethods.size() < 1)
			return null;// 成功更新0条数据
		List<Permission> pList = new ArrayList<Permission>();// 收集到的待新增的权限集合
		Permission permission = null;// 待添加的权限对象

		for (HandlerMethod method : handlerMethods) {// 遍历所有带有@RequestMapper注解的方法
			RequestMapping anno = method.getMethodAnnotation(RequestMapping.class);// 从控制器映射方法上取出@RequestMapper注解对象
			// @RequestMapper注解有没有name备注是作为一个权限的标志
			if (!"".equals(anno.name())) {// @RequestMapper注解写了name属性才做权限收集：所以@RequestMapper注解有没有name备注是作为一个权限的标志
				RequestMapping namespaceMapping = method.getBeanType().getAnnotation(RequestMapping.class);// 带有@RequestMapper注解的方法所在控制器类上的RequestMapping注解对象
				String namespace = namespaceMapping.value()[0];// 得到RequestMapping注解的value值,即命名空间,即模块
				String permissionValue = (namespace + ":" + anno.value()[0]).replace("/", "");// 得到权限 ,例如：user:delete
																								// 用户模块的删除权限
				if (pList.contains(permissionValue))
					continue;// 如果已经收集到该权限,则忽略不再重复收集
				// 构造权限对象,三个参数:权限,模块说明,权限说明
				permission = new Permission(permissionValue, namespaceMapping.name(), anno.name());// 把权限控制注解@Permission解析为权限控制对象SysPermission,方便插入数据库权限表
				pList.add(permission);// 把数据库没有存储的,收集容器中也没有收集到的的权限加入收集容器中.
			}
		}
		log.info("pList==============================>"+pList);
		return pList;
	}

}
*/
