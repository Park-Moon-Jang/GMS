
package com.goods.app.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public Map<String, Object> itemlist(@RequestParam Map<String,Object> map, ItemVO vo, @RequestParam("from_Date") java.sql.Date from_Date, @RequestParam("to_Date") java.sql.Date to_Date) {
//		변수 생략 : ItemVO vo, @RequestParam("from_Date") java.sql.Date from_Date, @RequestParam("to_Date") java.sql.Date to_Date
		
		System.out.println("컨트롤러 온 map"+ map);
		
		Map<String, Object> selInfo = new HashMap<String, Object>();
		
		selInfo.put("company_No", vo.getCompany_No());
		selInfo.put("category_No", vo.getCategory_No());
		selInfo.put("from_Date", from_Date);
		selInfo.put("to_Date", to_Date);

		
		Set<String> key = selInfo.keySet();
		
		for(String i : key) {
			
			System.out.println(selInfo.get(i)+":"+selInfo.get(i).getClass());
		}
		
		List<ItemVO> list = ms.getItemlist(selInfo); //여기서부터!!! 아이템 가져오는 거 시작하자!!!!
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
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

