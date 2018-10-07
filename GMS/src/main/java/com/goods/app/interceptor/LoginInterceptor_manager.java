package com.goods.app.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.goods.app.service.ManagerService;

import com.goods.app.vo.ManagerVO;


public class LoginInterceptor_manager extends HandlerInterceptorAdapter {

	@Inject
	ManagerService ser;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String manager_id = request.getParameter("manager_id");
		String manager_pw = request.getParameter("manager_pw");
		
		ManagerVO vo = ser.checkManager(manager_id, manager_pw);
		if (vo==null)
		{
			response.sendRedirect(request.getContextPath()+"/");
			return false;
		}
		HttpSession session = request.getSession();
		
		session.setAttribute("session_manager", vo.getManager_id());
		response.sendRedirect(request.getContextPath()+"/manager/login");
		
		return false;
	}
}
