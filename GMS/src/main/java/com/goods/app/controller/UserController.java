package com.goods.app.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.goods.app.service.UserService;
import com.goods.app.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Inject
	UserService ser;
	

	@RequestMapping(value = "/login" , method = RequestMethod.GET )
	public String login() 
	{
		HttpSession Session = null;
		System.out.println(Session.getAttribute("session_id"));
		return "home2"; 
	}
	@RequestMapping(value="/loginPost", method = RequestMethod.POST)
	public void loginPost(@ModelAttribute UserVO vo, HttpSession session, Model model) {
	}
	
	
	
	
}