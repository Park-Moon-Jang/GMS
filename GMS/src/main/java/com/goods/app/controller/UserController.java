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
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Inject
	UserService ser;
	
	@RequestMapping(value = "/login" , method = RequestMethod.GET )
	public String login(Model model) 
	{
		return "/user/userMain"; 
	}
	@RequestMapping(value="/loginPost", method = RequestMethod.POST)
	public void loginPost(@ModelAttribute UserVO vo, HttpSession session, Model model) {
	}
	

	@RequestMapping(value ="/main" )
	public String test(Model model) {
		

		return "/user/userMain";
	}
	
	@RequestMapping("/item")
	public String item(Model model) {
		

		return "/user/userItem";
	}
	@RequestMapping("/last")
	public String last(Model model) {
		

		return "/user/userLast";
	}
	@RequestMapping("/scrape")
	public String scrape(Model model) {
		

		return "/user/userScrape";
	}
	@RequestMapping("/page")
	public String page(Model model) {
		

		return "/user/userPage";
	}
}

