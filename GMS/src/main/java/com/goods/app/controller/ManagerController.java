package com.goods.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goods.app.service.ManagerService;
import com.goods.app.vo.ItemVO;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	ManagerService ms;


	@RequestMapping("/managerhome")
	public ModelAndView managerhome (Model model) {
	
		List<ItemVO> itemlist = ms.getItemlist();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", itemlist);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("managerhome");  //managerhome.jsp 로 간다
		return mav;
		
	}
	@RequestMapping("/managerboard")
	public ModelAndView mboard (Model model) {
	
		List<ItemVO> itemlist = ms.getItemlist();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", itemlist);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("managerboard");  //managerhome.jsp 로 간다
		return mav;
		
	}
	
	@RequestMapping("/viewmanageuser")
	public String viewitemreturned (Model model) {
		
		return "itemreturned";
	}
	
	
}
