package com.goods.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goods.app.service.TestService;
import com.goods.app.vo.TestVO;
@Component 
@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	TestService ts;
	
	@RequestMapping("/insert")
	public void insert(@ModelAttribute TestVO vo) {
		
		System.out.println("controller!");
		ts.insert(vo);
		
	}
	
	
	
	
	
	
	
}
