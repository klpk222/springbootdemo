package com.dj.springboot.common.filter;

import com.dj.springboot.common.auth.AccountInfo;
import com.dj.springboot.common.auth.AccountInfoContext;
import com.dj.springboot.common.base.ResponseData;
import com.dj.springboot.common.enums.ResponseCode;
import com.dj.springboot.common.util.JWTUtils;
import com.dj.springboot.common.util.JsonUtils;
import io.jsonwebtoken.Claims;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:API调用权限控制
 * @author:GuoYi
 *
 */

public class HttpBasicAuthorizeFilter implements Filter, HandlerInterceptor {

	JWTUtils jwtUtils = JWTUtils.getInstance();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext context = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        //Authorization
		String auth = httpRequest.getHeader("auth");
		//健康检查控制
		String uri = httpRequest.getRequestURI();

		System.out.println("**************************"+uri);

		if (uri.equals("/account/login") //登录
		) {
			long startTime = System.currentTimeMillis();
			chain.doFilter(request, response);
			long endTime = System.currentTimeMillis();
			System.out.println("该用户的请求已经处理完毕，请求花费的时间为：" + (endTime - startTime));
		} else {
			//验证TOKEN
			if (!StringUtils.hasText(auth)) {
				PrintWriter print = httpResponse.getWriter();
				print.write(JsonUtils.toJson(ResponseData.fail(ResponseCode.NO_AUTH_CODE.getCode(), "非法请求【缺少Authorization信息】")));
				return;
			}
			JWTUtils.JWTResult jwt = jwtUtils.checkToken(auth);
			if (!jwt.isStatus()) {
				PrintWriter print = httpResponse.getWriter();
				print.write(JsonUtils.toJson(ResponseData.fail(jwt.getCode(), jwt.getMsg())));
				return;
			}
			//获取登录的用户信息
			Claims claims = jwt.getClaims();
			System.out.println(claims);
			AccountInfo accountInfo = new AccountInfo();
			accountInfo.setUsername(String.valueOf(claims.get("username")));
			accountInfo.setPassword(String.valueOf(claims.get("password")));
			AccountInfoContext.setUser(accountInfo);
			chain.doFilter(httpRequest, response);
		}

	}
	
	@Override
	public void destroy() {
		
	}

}
