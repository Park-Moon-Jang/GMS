package com.goods.app.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public String login() 
	{
		return "/user/userMain"; 
	}
	@RequestMapping(value="/loginPost", method = RequestMethod.POST)
	public void loginPost(@ModelAttribute UserVO vo, HttpSession session, Model model) 
	{
	}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		return "home";
	}
	
	@RequestMapping(value="/joinPost",method=RequestMethod.POST)
	public String joinPost(@ModelAttribute UserVO vo)throws Exception 
	{
		ser.join(vo);
		return "home";
	}
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() 
	{
		return "join";
	}
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public String find()
	{
		return "/find/find";
	}
	@RequestMapping(value="/findID", method=RequestMethod.POST)
	public void findID(@ModelAttribute UserVO vo,HttpSession session)throws Exception
	{		
	}
	@RequestMapping(value="/findPW", method=RequestMethod.POST)
	public void findPW(@ModelAttribute UserVO vo,HttpSession session)throws Exception
	{		
	}
	@RequestMapping(value="/result", method=RequestMethod.GET)
	public String result()throws Exception
	{		
		return "/find/findID";
	}
	@RequestMapping(value="/resultPW", method=RequestMethod.GET)
	public String resultPW()throws Exception
	{		
		return "/find/findPW";
	}
	@RequestMapping(value="/updatePW", method=RequestMethod.POST)
	public String updatePW(@ModelAttribute UserVO vo,HttpSession session)throws Exception
	{	
		vo.setUser_id((session.getAttribute("find_id")).toString());
		ser.updatePW(vo);
		return "/home";
	}
	
	@RequestMapping(value ="/main" )
	public String test(Model model)
	{
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
	@RequestMapping("/updatePost")
	public String updatePost(Model model) {
		

		return "/user/userPage";
	}
		
}

