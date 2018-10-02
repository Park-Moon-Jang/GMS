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
import com.goods.app.vo.PhotoVO;
import com.goods.app.vo.SPostVO;
import com.goods.app.vo.UserVO;
import com.goods.app.vo.comentVO;


import java.util.ArrayList;
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
		return IList;
	}

	@ResponseBody
	@RequestMapping(value = "/categorySel", method = RequestMethod.POST)
	public List<ItemVO> categorySel(Model model, ItemVO IVO) {

		List<ItemVO> IList = ser.categorySel();
		return IList;
	}


	@RequestMapping(value = "/login" , method = RequestMethod.GET )
	public String login() 
	{
		return "forward:/user/main"; 
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
	
	@RequestMapping(value ="/main" )
	public String test(HttpSession session, Model model)
	{
		String user_Id = session.getAttribute("session_user").toString();
		List<ItemVO> IList = ser.myScrap(user_Id);
		List<SPostVO> SList = ser.mySPost(user_Id);
		model.addAttribute("IList", IList);
		model.addAttribute("SList", SList);
		return "/user/userMain";
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


	@RequestMapping("/item")
	public String item(Model model) {

		return "/user/userItem";
	}


	
	@RequestMapping("/suggestions")
	public String suggestions(Model model) {
		


		return "/user/userSuggestions";
	}

	@RequestMapping(value = "/detailSPost", method = RequestMethod.GET)
	public String detailSPost(Model model, @RequestParam(value="spost_No", required=false) int spost_No, HttpSession session) {
		session.setAttribute("session_spost", spost_No);
		ser.updateHits(spost_No);
		
		return "/user/userDetailSuggestions";
	}
	
	@RequestMapping("/insertSuggestions")
	public String insertSuggestions(Model model) {
		

		return "/user/userInsertSuggestions";
	}
	@RequestMapping("/updateSuggestions")
	public String updateSuggestions(Model model) {
		

		return "/user/userUpdateSuggestions";
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
		return IList;
	}

	@RequestMapping(value = "/itemDetail", method = RequestMethod.GET)
	public String itemDetail(Model model, @RequestParam(value = "item_No", required = false) int item_No,
			HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("session_Item_No", item_No);
		String user_Id = session.getAttribute("session_user").toString();
		
		session.setAttribute("session_scrape", ser.selectScrap(item_No, user_Id));
		List<Object> itemArray = new ArrayList();
		itemArray.add(session.getAttribute("session_Item_No").toString());
		List list = ser.selPhoto(itemArray);
		model.addAttribute("list", list);
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
		
		return count;
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
		List<Object> itemArray = new ArrayList();
		String str = "";
		for(ItemVO e : IList) {
			itemArray.add(e.getItem_No());
		}
		List list = ser.selPhoto(itemArray);
		Map map = new HashMap();
		map.put("IList", IList);
		map.put("sp", sp2);
		map.put("list", list);
		return map;
	}

	@ResponseBody
	@RequestMapping(value="/insertComent" , method = RequestMethod.POST)
	public int insertComnet(Model model, HttpSession session, @RequestParam(value = "coment") String coment) { 
		int item_No = (Integer) session.getAttribute("session_Item_No");
		String user_Id = session.getAttribute("session_user").toString();
		
		
		return ser.insertComent(item_No, user_Id, coment);
	}
	
	@ResponseBody
	@RequestMapping(value="/comentSel" , method = RequestMethod.POST)
	public Map comentSel(Model model, HttpSession session, @RequestParam("curPage") int curPage) { 
		
		
		int item_No = (Integer) session.getAttribute("session_Item_No");
		
		if(curPage==0) {
			curPage = 1;
		}
		
		int count = ser.selectComentCount(item_No);
		
		Paging sp3 = new Paging(count, curPage);
		List<comentVO> CList = ser.selectComent(item_No, curPage);
		Map map = new HashMap();
		map.put("sp", sp3);
		map.put("CList",CList);
		map.put("totalCount", count);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteComent" , method = RequestMethod.POST)
	public int deleteComent(Model model, HttpSession session, comentVO CVO) { 
		int item_No = (Integer) session.getAttribute("session_Item_No");
		int coment_No = CVO.getComent_No();
		
		return ser.deleteComent(item_No, coment_No);
	}
	
	@RequestMapping(value="/insertSuggestionsPost" , method = RequestMethod.POST)
	public String insertSuggestionsPost(Model model, HttpSession session, SPostVO SVO ) { 
		Map map = new HashMap();
		map.put("category_No", SVO.getCategory_No());
		map.put("title", SVO.getTitle());
		map.put("content", SVO.getContent());
		map.put("user_Id", session.getAttribute("session_user").toString());
		map.put("secret", SVO.getSecret());
		
		ser.insertSuggestionsPost(map);
		return "redirect:/user/suggestions";
	}
	
	@RequestMapping(value="/updateSuggestionsPost" , method = RequestMethod.POST)
	public String updateSuggestionsPost(Model model, HttpSession session, SPostVO SVO ) { 
		Map map = new HashMap();
		map.put("spost_No", SVO.getSpost_No());
		map.put("category_No", SVO.getCategory_No());
		map.put("title", SVO.getTitle());
		map.put("content", SVO.getContent());
		map.put("secret", SVO.getSecret());
		
		ser.updateSuggestionsPost(map);
		return "redirect:/user/suggestions";
	}
	
	@ResponseBody
	@RequestMapping(value="/SelSPost" , method = RequestMethod.POST)
	public Map SelSPost(Model model, HttpSession session, SPostVO SVO, @RequestParam("curPage") int curPage) { 
		if(curPage==0) {
			curPage = 1;
		}
		System.out.println(curPage);
		int count = ser.selectSPostCount();
		System.out.println(count);
//		
		Paging sp4 = new Paging(count, curPage);
		List<SPostVO> SList = ser.selectSPost(curPage);
		
		Map map = new HashMap();
		map.put("sp", sp4);
		map.put("SList", SList);
		return map;
		
	}
	@ResponseBody
	@RequestMapping(value="/delSPost" , method = RequestMethod.POST)
	public int delSPost(Model model, HttpSession session, SPostVO SVO) { 

		
		return ser.delSPost(SVO.getSpost_No());
		
	}
	@ResponseBody
	@RequestMapping(value="/SelDetailSPost" , method = RequestMethod.POST)
	public List<SPostVO> SelDetailSPost(Model model, HttpSession session, SPostVO SVO) { 
		int spost_No = (Integer) session.getAttribute("session_spost");
		List<SPostVO> SList = ser.selDetailSPost(spost_No);
		return SList;
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="/photoSel" , method = RequestMethod.POST)
	public List photoSel(Model model, @RequestParam(value = "scrapArray[]") List<Object> scrapArray) { 
		
		return ser.selPhoto(scrapArray);
		
		
	}
}



