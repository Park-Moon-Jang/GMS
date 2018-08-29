package com.goods.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goods.app.service.TestService;
import com.goods.app.vo.TestVO;


@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	TestService ts;
	
	@RequestMapping("/insert")
	public String insert(@ModelAttribute TestVO vo) {
		
		System.out.println("controller!");
		ts.insert(vo);
		
		return "home";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam int category_no) {
		System.out.println(category_no);
		System.out.println("controller!");
		ts.delete(category_no);
		
		return "home";
	}
	
	@RequestMapping("/list")
	public ModelAndView list(Model model) {
		

		List<TestVO> tList = ts.getList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", tList);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("/home");  //home.jsp 로 간다
		return mav;
	}
	
	
	
	
	
	
	
}
