package com.goods.app.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.goods.app.service.UserService;
import com.goods.app.vo.UserVO;

public class FindInterceptor2 extends HandlerInterceptorAdapter {
	@Inject
	UserService ser;

	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String user_id = request.getParameter("user_id");
		String user_email = request.getParameter("user_email");
		
		UserVO vo = ser.findID(user_id, user_email);
		if (vo==null)
		{
			response.sendRedirect(request.getContextPath()+"/user/find");
			
			return false;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("find_id", vo.getUser_id());
		response.sendRedirect(request.getContextPath()+"/user/resultPW");
		
		return false;
	}
}
