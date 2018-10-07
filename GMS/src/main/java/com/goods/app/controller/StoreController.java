package com.goods.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StoreController {

	
	@RequestMapping("/viewstore")
	public String viewstore (Model model) {
		
		return "manager/storelist";
	}
	
	@RequestMapping("/statistics")
	public String statistics (Model model) {
		
		return "manager/statistics";
	}
	
}
