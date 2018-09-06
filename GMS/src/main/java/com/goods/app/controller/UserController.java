package com.goods.app.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.goods.app.service.UserService;
import com.goods.app.vo.ItemVO;
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
	public List<ItemVO> selBtn(Model model, ItemVO IVO) {
		List<ItemVO> IList = ser.selBtn(IVO.getCompany_No(),IVO.getCategory_No(),IVO.getStore_Name());
//		for(ItemVO a : IList) {
//			System.out.println("브랜드 : " + a.getItem_No());
//		}
		return IList;
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
}

