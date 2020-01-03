package com.cyj.oauthcenter.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description:身份验证失败的处理程序
 * @ClassName: MyAuthenticationSuccessHandler.java
 * @author ChenYongJia
 * @Date 2018年12月02日 晚上22：54
 * @Email chen87647213@163.com
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

	/**
	 * 用户认证失败
	 * @param req
	 * @param res
	 * @param auth
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException auth)
			throws IOException, ServletException {
		System.out.println("用户认证失败");
		res.sendRedirect("https://blog.csdn.net/Mrs_chens");
	}

}
