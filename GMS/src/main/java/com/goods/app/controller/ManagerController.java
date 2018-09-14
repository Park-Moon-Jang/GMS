package com.goods.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.goods.app.service.ManagerService;
import com.goods.app.vo.ItemVO;
import com.goods.app.vo.Paging;
import com.goods.app.vo.UserVO;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	ManagerService ms;
	
	@RequestMapping("/viewitemreturned")
	public String viewitemreturned (Model model) {
		
		return "manager/itemreturned";
	}
	
	@RequestMapping("/viewitemlist")
	public String viewitemlist (Model model) {
		
		return "manager/itemlist";
	}
	
	@ResponseBody
	@RequestMapping(value="/itemlist", method=RequestMethod.POST)
	public Map<String, Object> itemlist(@RequestParam Map<String,Object> map, 
										ItemVO vo, 
										@RequestParam("from_Date") java.sql.Date from_Date, 
										@RequestParam("to_Date") java.sql.Date to_Date, 
										@RequestParam(defaultValue = "1") int curPage) {
		
		Map<String, Object> selInfo = new HashMap<String, Object>();
		
		selInfo.put("company_No", vo.getCompany_No());
		selInfo.put("category_No", vo.getCategory_No());
		selInfo.put("from_Date", from_Date);
		selInfo.put("to_Date", to_Date);
		
		int count = ms.getCount(selInfo);
		
		Paging sp = new Paging(count, curPage);

		selInfo.put("sp", sp);

		List<ItemVO> list = ms.getItemlist(selInfo);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("sp", sp);
		return result;
	}
	
	@RequestMapping("/viewitemstored")
	public String viewitemstored (Model model) {
		
		return "manager/itemstored";
	}
	@RequestMapping("/viewitemreleased")
	public String viewitemreleased (Model model) {
		
		return "manager/itemreleased";
	}

	
	@RequestMapping(value = "/login" , method = RequestMethod.GET )
	public String login(Model model, HttpSession session, HttpServletRequest req) 
	{
		return "forward:/manager/managerhome";
	}
	@RequestMapping(value="/loginPost", method = RequestMethod.POST)
	public void loginPost(@ModelAttribute UserVO vo, HttpSession session, Model model) {
	}


	@RequestMapping("/managerboard")
	public ModelAndView mboard (Model model) {
	
		List<ItemVO> itemlist = ms.getnewItemlist();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", itemlist);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("manager/managerboard");  
		return mav;
		
	} 
	
	@RequestMapping("/viewmanageuser")
	public ModelAndView viewmanageuser (Model model) {
		List<UserVO> userlist = ms.getUserlist();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userlist", userlist);
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("manager/viewmanageuser");  
		return mav;
		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		return "home";
	}

	@RequestMapping(value="/deleteUser/{user_id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable("user_id") String user_id) {
		ms.delete(user_id);
		return "redirect:/manager/viewmanageuser";
	}

	
	
	
	@RequestMapping("/managerhome")
	public String managerhome (Model model, HttpSession session) {
	
		
		List<ItemVO> itemlist = ms.getnewItemlist();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", itemlist);
		model.addAttribute("itemlist", itemlist);
		
		return "manager/managerhome";
		
	}

	@ResponseBody
	@RequestMapping(value="/companySel", method = RequestMethod.POST)
	public List<ItemVO> companySel(Model model, ItemVO vo){

			return ms.companySel();
		
	}
	
	@ResponseBody
	@RequestMapping(value="/categorySel", method = RequestMethod.POST)
	public List<ItemVO> categorySel(Model model, ItemVO vo){
			
			return ms.categorySel();
			
	}
	
}


