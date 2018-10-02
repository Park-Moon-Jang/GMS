package com.goods.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goods.app.service.StoreService;
import com.goods.app.vo.ItemVO;

@Controller
@RequestMapping("/store")
public class StoreController {

	@Autowired
	StoreService ss;
	
	@RequestMapping("/viewstore")
	public String viewstore (Model model) {
		
		return "manager/storelist";
	}
	
	@RequestMapping("/statistics")
	public String statistics (Model model) {

		List<ItemVO>list = ss.selectList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		model.addAttribute("map", map);
		
		return "manager/statistics";
	}
	
}
