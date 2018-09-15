package com.goods.app.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession se=req.getSession(false);
		System.out.println("RequestURL "+req.getRequestURL());
		boolean login = false;
		if (se!=null)
		{
			System.out.println("세션있음");
			chain.doFilter(request, response);
		}
		System.out.println("세션없음");
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
