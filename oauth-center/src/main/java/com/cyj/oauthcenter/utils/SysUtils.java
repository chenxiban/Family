package com.cyj.oauthcenter.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;


/**
 * 系统权限工具类
 */
@Slf4j
public class SysUtils {

    /**
     * 类型: SysUser
     * Session中的当前用户对象
     */
    public static final String CURRENTUSER = "currentUser";
    /**
     * 类型: List<String> 字符串集合
     * Session中当前用户拥有的角色集合
     */
    public static final String CURRENTROLE = "currentRole";
    /**
     * 类型: List<String> 字符串集合
     * Session中当前用户拥有的权限集合
     */
    public static final String CURRENTPERMISSION = "currentPermission";

    //系统操作的命名空间
    public static final String SYSSTR = "sys";

    //登录操作的方法名
    public static final String LOGINSTR = "login";

    //不对匹配该值的访问路径拦截（正则）
    public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(mvc)|(app)|(weixin)|(static)|(main)|(websocket)).*";


    /**
     * 从请求映射方法上取出权限字符串
     *
     * @param method 请求映射方法
     * @return 权限字符串
     */
    public static String method2PermissionValue(HandlerMethod method) {
        //请求映射方法上的权限字符串
        String permissionValue = null;
        //从控制器映射方法上取出@RequestMapper注解对象
        RequestMapping anno = method.getMethodAnnotation(RequestMapping.class);
        //PreAuthorize pre=method.getMethodAnnotation(PreAuthorize.class);//从控制器映射方法上取出@PreAuthorize注解对象
        //@RequestMapper注解有没有name备注是作为一个权限的标志
        //@RequestMapper注解写了name属性才做权限收集：所以@RequestMapper注解有没有name备注是作为一个权限的标志
        if (!"".equals(anno.name())) {
            //带有@RequestMapper注解的方法所在控制器类上的RequestMapping注解对象
            RequestMapping namespaceMapping = method.getBeanType().getAnnotation(RequestMapping.class);
            //得到RequestMapping注解的value值,即命名空间,即模块
            String namespace = namespaceMapping.value()[0];
            //得到权限 ,例如：user:delete 用户模块的删除权限
            permissionValue = (namespace + ":" + anno.value()[0]).replace("/", "");
            log.info("得到权限=>" + permissionValue + "权限说明:" + anno.name());
            //构造权限对象,三个参数:权限,模块说明,权限说明
//			permission = new SysPermission(permissionValue,namespaceMapping.name(), anno.name());//把权限控制注解@Permission解析为权限控制对象SysPermission,方便插入数据库权限表
        }
        return permissionValue;
    }

    /**
     * 从请求映射方法上取出权限对象
     *
     * @param method 请求映射方法
     * @return 权限对象
     */
    public static SysPermission method2Permission(HandlerMethod method) {
        //请求映射方法上的权限对象
        SysPermission permission = null;
        //从控制器映射方法上取出@RequestMapper注解对象
        RequestMapping anno = method.getMethodAnnotation(RequestMapping.class);
        //@RequestMapper注解有没有name备注是作为一个权限的标志
        //@RequestMapper注解写了name属性才做权限收集：所以@RequestMapper注解有没有name备注是作为一个权限的标志
        if (!"".equals(anno.name())) {
            //带有@RequestMapper注解的方法所在控制器类上的RequestMapping注解对象
            RequestMapping namespaceMapping = method.getBeanType().getAnnotation(RequestMapping.class);
            //得到RequestMapping注解的value值,即命名空间,即模块
            String namespace = namespaceMapping.value()[0];
            //得到权限 ,例如：user:delete 用户模块的删除权限
            String permissionValue = (namespace + ":" + anno.value()[0]).replace("/", "");
            log.info("得到权限 ,例如：user:delete 用户模块的删除权限=>"+permissionValue +"权限说明:"+anno.name());
            // 构造权限对象,三个参数:权限,模块说明,权限说明，把权限控制注解@Permission解析为权限控制对象SysPermission,方便插入数据库权限表
            permission = new SysPermission(permissionValue, namespaceMapping.name(), anno.name());
        }
        return permission;
    }
	
	
/*
	因为正则表达式是一个很庞杂的体系，所以列出供参考。

	// 反斜杠
	/t 间隔 ('/u0009')
	/n 换行 ('/u000A')
	/r 回车 ('/u000D')
	/d 数字 等价于[0-9]
	/D 非数字 等价于[^0-9]
	/s 空白符号 [/t/n/x0B/f/r]
	/S 非空白符号 [^/t/n/x0B/f/r]
	/w 单独字符 [a-zA-Z_0-9]
	/W 非单独字符 [^a-zA-Z_0-9]
	/f 换页符
	/e Escape
	/b 一个单词的边界
	/B 一个非单词的边界
	/G 前一个匹配的结束

	^为限制开头
	^java     条件限制为以Java为开头字符
	$为限制结尾
	java$     条件限制为以java为结尾字符
	.  条件限制除/n以外任意一个单独字符
	java..     条件限制为java后除换行外任意两个字符


	加入特定限制条件「[]」
	[a-z]     条件限制在小写a to z范围中一个字符
	[A-Z]     条件限制在大写A to Z范围中一个字符
	[a-zA-Z] 条件限制在小写a to z或大写A to Z范围中一个字符
	[0-9]     条件限制在小写0 to 9范围中一个字符
	[0-9a-z] 条件限制在小写0 to 9或a to z范围中一个字符
	[0-9[a-z]] 条件限制在小写0 to 9或a to z范围中一个字符(交集)

	[]中加入^后加再次限制条件「[^]」
	[^a-z]     条件限制在非小写a to z范围中一个字符
	[^A-Z]     条件限制在非大写A to Z范围中一个字符
	[^a-zA-Z] 条件限制在非小写a to z或大写A to Z范围中一个字符
	[^0-9]     条件限制在非小写0 to 9范围中一个字符
	[^0-9a-z] 条件限制在非小写0 to 9或a to z范围中一个字符
	[^0-9[a-z]] 条件限制在非小写0 to 9或a to z范围中一个字符(交集)

	在限制条件为特定字符出现0次以上时，可以使用「*」
	J*     0个以上J
	.*     0个以上任意字符
	J.*D     J与D之间0个以上任意字符

	在限制条件为特定字符出现1次以上时，可以使用「+」
	J+     1个以上J
	.+     1个以上任意字符
	J.+D     J与D之间1个以上任意字符

	在限制条件为特定字符出现有0或1次以上时，可以使用「?」
	JA?     J或者JA出现

	限制为连续出现指定次数字符「{a}」
	J{2}     JJ
	J{3}     JJJ
	文字a个以上，并且「{a,}」
	J{3,}     JJJ,JJJJ,JJJJJ,???(3次以上J并存)
	文字个以上，b个以下「{a,b}」
	J{3,5}     JJJ或JJJJ或JJJJJ
	两者取一「|」
	J|A     J或A
	Java|Hello     Java或Hello
	 
*/


}
