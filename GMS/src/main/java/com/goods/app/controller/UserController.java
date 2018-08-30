package com.goods.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping(value = "/result" , method = RequestMethod.GET )
	public String result() 
	{
		return "userhome";
	}
	

	@RequestMapping(value = "/login" , method = RequestMethod.GET )
	public String login() 
	{
		return "home";
	}
	
	
	
	
}