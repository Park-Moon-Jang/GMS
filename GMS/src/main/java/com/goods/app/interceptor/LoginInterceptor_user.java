package com.goods.app.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.goods.app.service.UserService;
import com.goods.app.vo.ManagerVO;
import com.goods.app.vo.UserVO;

public class LoginInterceptor_user extends HandlerInterceptorAdapter {
	@Inject
	UserService ser;

	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		UserVO vo = ser.checkUser(id, pw);
		if (vo==null)
		{
			response.sendRedirect(request.getContextPath()+"/");
			
			return false;
		}
		HttpSession session = request.getSession();
		session.setAttribute("session_user", vo.getUser_id());
		response.sendRedirect(request.getContextPath()+"/user/login");
		
		return false;
	}
}
