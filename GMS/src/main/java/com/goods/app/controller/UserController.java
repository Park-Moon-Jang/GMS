package com.goods.app.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.goods.app.service.UserService;
import com.goods.app.vo.ItemVO;
import com.goods.app.vo.Paging;
import com.goods.app.vo.UserVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Inject
	UserService ser;
	
	@ResponseBody
	@RequestMapping(value="/selBtn" , method = RequestMethod.POST)
	public Map selBtn(Model model, ItemVO IVO, @RequestParam("curPage") int curPage) {

		if(curPage==0) {
			curPage = 1;
		}
		
		int count = ser.selectCount(IVO);
		System.out.println(count);
		
		Paging sp = new Paging(count, curPage);
		
		List<ItemVO> IList = ser.selBtn(IVO.getCompany_No(),IVO.getCategory_No(),IVO.getStore_Name(), curPage);
		Map map = new HashMap();
//		for(ItemVO a : IList) {
//			System.out.println("브랜드 : " + a.getItem_No());
//		}
		map.put("IList", IList);
		map.put("sp", sp);
		
		return map;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/storeSel" , method = RequestMethod.POST)
	public List<ItemVO> storeSel(Model model, ItemVO IVO) {
		
		List<ItemVO> IList = ser.storeSel();
		return IList;
	}
	
	@ResponseBody
	@RequestMapping(value="/brandSel" , method = RequestMethod.POST)
	public List<ItemVO> brandSel(Model model, ItemVO IVO) {
		
		List<ItemVO> IList = ser.brandSel();
//		for(ItemVO a : IList) {
//			System.out.println("브랜드 : " + a.getComPany_Name());
//		}
		return IList;
	}
	
	@ResponseBody
	@RequestMapping(value="/categorySel" , method = RequestMethod.POST)
	public List<ItemVO> categorySel(Model model, ItemVO IVO) {
		
		List<ItemVO> IList = ser.categorySel();
//		for(ItemVO a : IList) {
//			System.out.println("브랜드 : " + a.getCategory_Name());
//		}
		return IList;
	}
	
	
	@RequestMapping(value = "/login" , method = RequestMethod.GET )
	public String login(Model model) 
	{
		return "/user/userMain"; 
	}
	@RequestMapping(value="/loginPost", method = RequestMethod.POST)
	public void loginPost(@ModelAttribute UserVO vo, HttpSession session, Model model) {
	}
	@RequestMapping(value="/joinPost",method=RequestMethod.POST)
	public String joinPost(@ModelAttribute UserVO vo)throws Exception {
		ser.join(vo);
		return "home";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "join";
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
	@ResponseBody
	@RequestMapping(value="/	" , method = RequestMethod.POST)
	public List<ItemVO> itemDetalSel(Model model, HttpSession session) { 
		
//		System.out.println(session.getAttribute("item_No"));
		int item_No = (Integer) session.getAttribute("item_No");
		List<ItemVO> IList = ser.itemDetalSel(item_No);
//		for(ItemVO a : IList) {
//			System.out.println("브랜드 : " + a.getCategory_Name());
//		}
		return IList;
	}
	
	@RequestMapping(value="/itemDetail", method = RequestMethod.GET)
	public String itemDetail(Model model, @RequestParam(value="item_No", required=false) int item_No, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("item_No", item_No);

		return "/user/userItemDetail";
	}
}

