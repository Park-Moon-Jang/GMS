/*package com.goods.app.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.goods.app.service.UserService;
import com.goods.app.vo.ManagerVO;
import com.goods.app.vo.UserVO;

public class LoginInterceptor_manager extends HandlerInterceptorAdapter {

	@Inject
	ManagerService ser;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserVO vo = ser.checkUser(id, pw);
		if (vo==null)
		{
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		HttpSession session = request.getSession();
		session.setAttribute("session_id", vo.getUser_id());
		session.setAttribute("session_identity", "user");
		
		response.sendRedirect(request.getContextPath()+"/");
		
		return false;
	}
}
*/