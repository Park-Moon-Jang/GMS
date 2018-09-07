
package com.goods.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goods.app.service.ManagerService;
import com.goods.app.vo.ItemVO;
import com.goods.app.vo.UserVO;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	ManagerService ms;
	
	
	@RequestMapping("/viewmanageuser")
	public String viewitemreturned (Model model) {
		
		return "manager/itemreturned";
	}
	@RequestMapping("/viewitemlist")
	public String viewitemlist (Model model) {
		
		return "manager/itemlist";
	}
	
	@ResponseBody
	@RequestMapping(value="/itemlist", method=RequestMethod.POST)
	public Map<String, Object> itemlist(ModelAndView mav, @RequestParam Map<String,Object> map) {
		
		System.out.println("컨트롤러 온 map"+ map);
//		mav.addObject("success", "success");
		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO a = new ItemVO();
		ItemVO b = new ItemVO();
		a.setItem_Name("aa");
		a.setCompany_No(10);
		a.setCompany_No(10);
		a.setAmount(100);
		a.setCarry_Date(new Date());
		list.add(a);
		
		Map<String, Object> retVal = new HashMap<String, Object>();
		retVal.put("list", list);
		retVal.put("code", "OK");
		return retVal;
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
		System.out.println("login: "+session.getAttribute("session_manager"));
		return "forward:/manager/managerhome";
	}
	@RequestMapping(value="/loginPost", method = RequestMethod.POST)
	public void loginPost(@ModelAttribute UserVO vo, HttpSession session, Model model) {
	}

	@RequestMapping("/managerhome")
	public String managerhome (Model model, HttpSession session) {
	
		
		List<ItemVO> itemlist = ms.getItemlist();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", itemlist);
		model.addAttribute("itemlist", itemlist);
		model.addAttribute(session);
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("map", map);
//		System.out.println("managerhome session: " + session.getId()+ session.getAttribute("session_manager"));
//		mav.addObject(session);
//		mav.setViewName("manager/managerhome");  //managerhome.jsp 로 간다
		return "manager/managerhome";
		
	}
	@RequestMapping(value="/managerboard", method = RequestMethod.POST)
	public ModelAndView mboard (Model model) {
	
		List<ItemVO> itemlist = ms.getItemlist();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", itemlist);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("manager/managerboard");  
		return mav;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/companySel", method = RequestMethod.POST)
	public List<ItemVO> companySel(Model model, ItemVO vo){
		
		System.out.println("컴퍼니셀렉트");
			List<ItemVO> iList = ms.companySel();
			for(ItemVO i : iList) {
				System.out.println("생산업체 이름들 목록"+ i.getCompany_No()+i.getCompany_Name());
			}
			return iList;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/categorySel", method = RequestMethod.POST)
	public List<ItemVO> categorySel(Model model, ItemVO vo){
		
		System.out.println("카테고리셀렉트");
			List<ItemVO> iList = ms.categorySel();
			for(ItemVO i : iList) {
				System.out.println("상품유형 목록"+ i.getCategory_No()+i.getCategory_Name());
			}
			return iList;
		
	}
	
}

