package com.goods.app.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.goods.app.service.ManagerService;
import com.goods.app.service.UserService;
import com.goods.app.vo.ItemVO;
import com.goods.app.vo.M_boardVO;
import com.goods.app.vo.Paging;


import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;



import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;



import com.goods.app.vo.PhotoVO;
import com.goods.app.vo.SComentVO;
import com.goods.app.vo.SPostVO;
import com.goods.app.vo.UserVO;


@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	MappingJackson2JsonView jsonView;
	
	
	@Autowired
	ManagerService ms;
	
	@Autowired
	UserService ser;
	
	@RequestMapping("/viewitemreturned")
	public String viewitemreturned (Model model) {
		
		return "manager/itemreturned";
	}
	
	@RequestMapping("/viewitemlist")
	public String viewitemlist (Model model) {
		
		return "manager/itemlist";
	}
	
	@ResponseBody
	@RequestMapping(value="/itemlist", method=RequestMethod.POST)
	public Map<String, Object> itemlist(@RequestParam Map<String,Object> map, 
										ItemVO vo, 
										@RequestParam("from_Date") java.sql.Date from_Date, 
										@RequestParam("to_Date") java.sql.Date to_Date, 
										@RequestParam(defaultValue = "1") int curPage) {
		
		Map<String, Object> selInfo = new HashMap<String, Object>();
		
		selInfo.put("item_Name", vo.getItem_Name());
		selInfo.put("company_No", vo.getCompany_No());
		selInfo.put("category_No", vo.getCategory_No());
		selInfo.put("from_Date", from_Date);
		selInfo.put("to_Date", to_Date);
		
		int count = ms.getCount(selInfo);
		System.out.println("移댁슫�듃: " + count);
		Paging sp = new Paging(count, curPage);

		selInfo.put("sp", sp);

		System.out.println("itemlist - vo info"+ vo.getItem_Name()+":"+vo.getCompany_No()+":"+vo.getCategory_No()+":"+from_Date+":"+to_Date);
		
		List<ItemVO> list = ms.getItemlist(selInfo);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("sp", sp);
		return result;
	}
	
	@RequestMapping("/viewitemstored")
	public String viewitemstored (Model model) {
		
		return "manager/itemstored";
	}
	
	@ResponseBody
	@RequestMapping(value="/itemstored", method=RequestMethod.POST)
	public Map<String, Object> itemstored(@RequestParam Map<String,Object> map, 
										ItemVO vo, 
										@RequestParam("from_Date") java.sql.Date from_Date, 
										@RequestParam("to_Date") java.sql.Date to_Date, 
										@RequestParam(defaultValue = "1") int curPage) {
		
		System.out.println("�쁽�옱 �럹�씠吏� :"+curPage);
		
		Map<String, Object> selInfo = new HashMap<String, Object>();
		
		selInfo.put("item_Name", vo.getItem_Name());
		selInfo.put("company_No", vo.getCompany_No());
		selInfo.put("category_No", vo.getCategory_No());
		selInfo.put("from_Date", from_Date);
		selInfo.put("to_Date", to_Date);
		
		int count = ms.getCount(selInfo);
		System.out.println("移댁슫�뱶 :"+ count);
		Paging sp = new Paging(count, curPage);

		selInfo.put("sp", sp);

		System.out.println("itemstored - vo info"+ vo.getItem_Name()+":"+vo.getCompany_No()+":"+vo.getCategory_No()+":"+from_Date+":"+to_Date);
		
		List<ItemVO> list = ms.getItemlist(selInfo);
		
		for(ItemVO  v : list) {
			
			System.out.println(v.getItem_Name());
			
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("sp", sp);
		return result;
	}
	
	@RequestMapping("/viewitemregister")
	public String viewitemregister(Model model) {
		
		return "manager/itemregister";
		
	}
	
	@ResponseBody
	@RequestMapping("/itemregister.do")
	public Map<String, Object> itemregister(MultipartHttpServletRequest multi) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String checkStr	 = multi.getParameter("company") +multi.getParameter("category") + multi.getParameter("registerNum");
		int checkNum = Integer.parseInt(checkStr);
		
		System.out.println(checkNum);
		
		if(ms.checkregiNum(checkNum) == 0) {
			result.put("check", "�엯怨좊벑濡�");
			
			ItemVO ivo = new ItemVO();
			ivo.setItem_No(checkNum);  //�뿬湲� �솢 �꼸�씠吏�??
			ivo.setItem_Name(multi.getParameter("item_Name"));
			ivo.setCompany_No(Integer.parseInt(multi.getParameter("company")));
			ivo.setCategory_No(Integer.parseInt(multi.getParameter("category")));
			ivo.setAmount(Integer.parseInt(multi.getParameter("amount")));
			ivo.setPrice(Integer.parseInt(multi.getParameter("price")));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date;
			try {
				date = sdf.parse(multi.getParameter("carry_Date"));
				java.sql.Date sqlDate  =new java.sql.Date(date.getTime()); 
				ivo.setCarry_Date(sqlDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ms.registerItem(ivo);
			
			PhotoVO pvo = new PhotoVO();
			pvo.setItem_No(checkNum);
			pvo.setPhoto_Name(multi.getParameter("photo_Name"));
			try {
				byte[] byteArray = multi.getFile("photo_Data").getBytes();
				pvo.setPhoto_Data(byteArray);
			
			} catch (IOException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}			

			ms.registerPhoto(pvo);
			System.out.println("pvo �꽦怨�");

			return result;
			
		}else {
			result.put("check", "�엯怨좊쾲�샇 以묐났");
			return result;
		}

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



	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		return "home";
	}

	
	
	
	
	@RequestMapping("/managerhome")
	public String managerhome (Model model, HttpSession session) {
	
		List<ItemVO> itemlist = ms.getnewItemlist();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", itemlist);
		model.addAttribute("itemlist", itemlist);
		List<ItemVO>list = ms.selectList();
		map.put("list", list);
		model.addAttribute("map", map);
		List<M_boardVO> boardlist = ms.getBoardlist2();
		map.put("boardlist", boardlist);
		List<SPostVO> SList = ms.selectSPost();
		model.addAttribute("SList",SList);
		
		return "manager/managerhome";
		
	}

	@RequestMapping("/managerboard")
	public ModelAndView managerboard (Model model) {
		List<M_boardVO> boardlist = ms.getBoardlist();
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("boardlist", boardlist);
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("manager/managerboard");  
		return mav;
		
	} 
	@RequestMapping(value ="/updateboard/{no}" , method = RequestMethod.GET)
	public ModelAndView updateboard (@PathVariable("no") int no) {
		M_boardVO boardvo=ms.selectboard(no);
		Map<String,M_boardVO> map = new HashMap<String,M_boardVO>();
		map.put("boardvo", boardvo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("manager/updateBoard");  
		return mav;
	}
	
	@RequestMapping("/managerSPost")
	public String managerSPost (Model model) {
		return "manager/managerSPost";
		
	}

	@RequestMapping(value= "/managerSPostDetail", method = RequestMethod.GET)
	public String managerSPostDetail (Model model, HttpSession session, @RequestParam(value="spost_No") int spost_No) {
		session.setAttribute("spost_No", spost_No);
		List<SPostVO> SList = ser.selDetailSPost(spost_No);
		List<SComentVO> Coment = ser.selectSPostComent(spost_No);
		model.addAttribute("SList",SList);
		model.addAttribute("Coment",Coment);
		return "manager/managerSPostDetail";
		
	}
	
	@RequestMapping("/viewmanageuser")
	public ModelAndView viewmanageuser (Model model) {
		List<UserVO> userlist = ms.getUserlist();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userlist", userlist);
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("manager/viewmanageuser");  
		return mav;
		
	}
	@RequestMapping("/insertBoard")
	public String insertBoard (@ModelAttribute M_boardVO vo) {
		ms.insertboard(vo);
		
		return "redirect:/manager/managerboard";
		
	}
	@RequestMapping(value="/deleteUser/{user_id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable("user_id") String user_id) {
		ms.delete(user_id);
		return "redirect:/manager/viewmanageuser";
	}

	@RequestMapping(value="/deleteBoard/{board_no}", method=RequestMethod.GET)
	public String deleteBoard(@PathVariable("board_no") int board_no) {
		ms.deleteBoard(board_no);
		return "redirect:/manager/managerboard";
	}
	@RequestMapping("/insertForm")
	public String insertForm (Model model) {
		
		return "manager/insertForm";
		
	}
	@RequestMapping(value ="/boardDetail/{board_no}" , method = RequestMethod.GET)
	public ModelAndView boardDetail (@PathVariable("board_no") int board_no) {
		M_boardVO boardvo=ms.selectboard(board_no);
		Map<String,M_boardVO> map = new HashMap<String,M_boardVO>();
		map.put("boardvo", boardvo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("manager/boardDetail");  
		return mav;
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
	@RequestMapping(value="/updateform", method = RequestMethod.POST)
	public String updateform (@ModelAttribute M_boardVO vo){
		ms.updateform(vo);
		 
		return "redirect:/manager/managerboard";
		
	}
	@ResponseBody
	@RequestMapping(value="/SPostList" , method = RequestMethod.POST)
	public Map SPostList(Model model, HttpSession session, SPostVO SVO, @RequestParam("curPage") int curPage) { 
		if(curPage==0) {
			curPage = 1;
		}
		int count = ms.SPostCount();
//		
		Paging sp = new Paging(count, curPage);
		List<SPostVO> SList = ms.selectSPost(curPage);
		Map map = new HashMap();
		map.put("sp", sp);
		map.put("SList", SList);
		return map;
		
	}
	@ResponseBody
	@RequestMapping(value="/insertSPostComent", method = RequestMethod.POST)
	public int insertSPostComent(Model model, HttpSession session, SPostVO SVO, @RequestParam("coment") String coment) {
		int spost_No = (Integer) session.getAttribute("spost_No");
		String manager_Id = session.getAttribute("session_manager").toString();
		return ms.insertSPostComent(spost_No,coment,manager_Id);
		
	}
}


