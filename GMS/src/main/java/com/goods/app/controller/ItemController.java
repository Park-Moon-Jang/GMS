package com.goods.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemController {

	@RequestMapping("/viewitemlist")
	public String viewitemlist (Model model) {
		
		return "itemlist";
	}
	@RequestMapping("/viewitemstored")
	public String viewitemstored (Model model) {
		
		return "itemstored";
	}
	@RequestMapping("/viewitemreleased")
	public String viewitemreleased (Model model) {
		
		return "itemreleased";
	}
	@RequestMapping("/viewitemreturned")
	public String viewitemreturned (Model model) {
		
		return "itemreturned";
	}
}
