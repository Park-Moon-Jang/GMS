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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping(value = "/selBtn", method = RequestMethod.POST)
	public Map selBtn(Model model, ItemVO IVO, @RequestParam("curPage") int curPage) {

		if (curPage == 0) {
			curPage = 1;
		}

		int count = ser.selectCount(IVO);
		System.out.println(count);

		Paging sp = new Paging(count, curPage);

		List<ItemVO> IList = ser.selBtn(IVO.getCompany_No(), IVO.getCategory_No(), IVO.getStore_Name(), curPage);
		Map map = new HashMap();
//		for(ItemVO a : IList) {
//			System.out.println("�귣�� : " + a.getItem_No());
//		}
		map.put("IList", IList);
		map.put("sp", sp);

		return map;

	}

	@ResponseBody
	@RequestMapping(value = "/storeSel", method = RequestMethod.POST)
	public List<ItemVO> storeSel(Model model, ItemVO IVO) {

		List<ItemVO> IList = ser.storeSel();
		return IList;
	}

	@ResponseBody
	@RequestMapping(value = "/brandSel", method = RequestMethod.POST)
	public List<ItemVO> brandSel(Model model, ItemVO IVO) {

		List<ItemVO> IList = ser.brandSel();
//		for(ItemVO a : IList) {
//			System.out.println("�귣�� : " + a.getComPany_Name());
//		}
		return IList;
	}

	@ResponseBody
	@RequestMapping(value = "/categorySel", method = RequestMethod.POST)
	public List<ItemVO> categorySel(Model model, ItemVO IVO) {

		List<ItemVO> IList = ser.categorySel();
//		for(ItemVO a : IList) {
//			System.out.println("�귣�� : " + a.getCategory_Name());
//		}
		return IList;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/user/userMain";
	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPost(@ModelAttribute UserVO vo, HttpSession session, Model model) {
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "home";
	}

	@RequestMapping(value = "/joinPost", method = RequestMethod.POST)
	public String joinPost(@ModelAttribute UserVO vo) throws Exception {
		ser.join(vo);
		return "home";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find() {
		return "/find/find";
	}

	@RequestMapping(value = "/findID", method = RequestMethod.POST)
	public void findID(@ModelAttribute UserVO vo, HttpSession session) throws Exception {
	}

	@RequestMapping(value = "/findPW", method = RequestMethod.POST)
	public void findPW(@ModelAttribute UserVO vo, HttpSession session) throws Exception {
	}

	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result() throws Exception {
		return "/find/findID";
	}

	@RequestMapping(value = "/resultPW", method = RequestMethod.GET)
	public String resultPW() throws Exception {
		return "/find/findPW";
	}

	@RequestMapping(value = "/updatePW", method = RequestMethod.POST)
	public String updatePW(@ModelAttribute UserVO vo, HttpSession session) throws Exception {
		vo.setUser_id((session.getAttribute("find_id")).toString());
		ser.updatePW(vo);
		return "/home";
	}

	@RequestMapping(value = "/updatePost", method = RequestMethod.POST)
	public String updatePost(@ModelAttribute UserVO vo, HttpSession session) throws Exception {

		ser.update(vo);
		return "/user/userPage";
	}

	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	public String deletePost(@ModelAttribute UserVO vo, HttpSession session) throws Exception {
		session.invalidate();
		ser.delete_scr(vo.getUser_id());
		ser.delete(vo);
		
		return "redirect:/";
	}

	@RequestMapping(value = "/main")
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
	@RequestMapping(value="/itemDetalSel" , method = RequestMethod.POST)
	public List<ItemVO> itemDetalSel(Model model, HttpSession session) { 
		

//		System.out.println(session.getAttribute("item_No"));
		int item_No = (Integer) session.getAttribute("session_Item_No");
		List<ItemVO> IList = ser.itemDetalSel(item_No);
//		for(ItemVO a : IList) {
//			System.out.println("�귣�� : " + a.getCategory_Name());
//		}
		return IList;
	}

	@RequestMapping(value = "/itemDetail", method = RequestMethod.GET)
	public String itemDetail(Model model, @RequestParam(value = "item_No", required = false) int item_No,
			HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("session_Item_No", item_No);
		String user_Id = session.getAttribute("session_user").toString();
		
		session.setAttribute("session_scrape", ser.selectScrap(item_No, user_Id));

		return "/user/userItemDetail";
	}

	@ResponseBody
	@RequestMapping(value = "/checkid")
	public Map<Object, Object> checkid(@RequestBody String user_id) {

		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();

		count = ser.checkid(user_id);
		map.put("cnt", count);

		return map;
	}


	
	@ResponseBody
	@RequestMapping(value="/insertScrap" , method = RequestMethod.POST)
	public int insertScrap(Model model, HttpSession session) { 
		
		return ser.insertScrap(session.getAttribute("session_user").toString(), (Integer) session.getAttribute("session_Item_No"));
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteScrap" , method = RequestMethod.POST)
	public int deleteScrap(Model model, HttpSession session) { 
		
		return ser.deleteScrap(session.getAttribute("session_user").toString(), (Integer) session.getAttribute("session_Item_No"));
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteMyScrap" , method = RequestMethod.POST)
	public int deleteScrap(Model model, HttpSession session, ItemVO IVO) { 
		
		return ser.deleteScrap(session.getAttribute("session_user").toString(), IVO.getItem_No());
	}
	
	@ResponseBody
	@RequestMapping(value="/selectedScrapDelete" , method = RequestMethod.POST)
	public int selectedScrapDelete(Model model, HttpSession session, @RequestParam(value = "checkArray[]") List<String> checkArray) { 
		String user_Id = session.getAttribute("session_user").toString();
		int count = ser.selectedScrapDelete(checkArray, user_Id);
		
		return 0;
	}
	
	@ResponseBody
	@RequestMapping(value="/myScrapSel" , method = RequestMethod.POST)
	public Map myScrapSel(Model model, HttpSession session, @RequestParam("curPage") int curPage) { 
		if(curPage==0) {
			curPage = 1;
		}
		String user_Id = session.getAttribute("session_user").toString();
		
		int count = ser.myScrapCount(user_Id);
		System.out.println(count);
		Paging sp2 = new Paging(count, curPage); 
		
		List<ItemVO> IList = ser.myScrapSel(user_Id, curPage);
		Map map = new HashMap();
		map.put("IList", IList);
		map.put("sp", sp2);
		return map;
	}

}
