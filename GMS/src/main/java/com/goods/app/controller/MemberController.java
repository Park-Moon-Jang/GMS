package com.goods.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goods.app.vo.TestVO;

@Controller
@RequestMapping("/user")
public class MemberController {

	@RequestMapping("/test")
	public String test(Model model) {
		

		return "/user/userMain";
	}
}
