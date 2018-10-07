package com.goods.app.controller;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.goods.app.service.ManagerService;
import com.goods.app.vo.ItemVO;
import com.goods.app.vo.M_boardVO;
import com.goods.app.vo.Paging;
import com.goods.app.vo.PhotoVO;
import com.goods.app.vo.UserVO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;



@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	ManagerService ms;
	
	@RequestMapping("/viewitemreturned")
	public String viewitemreturned (Model model) {
		
		return "manager/itemreturned";
	}
	
	@RequestMapping("/viewitemlist")
	public String viewitemlist (Model model) {
		
		return "manager/itemlist";
	}
	
	@SuppressWarnings("restriction")
	@RequestMapping(value = "/viewitemupdate", method = RequestMethod.GET)
	public String viewitemupdate (Model model, @RequestParam(value="item_No", required=false) int item_No, ItemVO ivo, PhotoVO pvo) {
		
		ivo = ms.getItemInfo(item_No);
		pvo = ms.getItemPhoto(item_No);
		
		String encoded_Photo = Base64.encode(pvo.getPhoto_Data());
		
		model.addAttribute("ivo", ivo);
		model.addAttribute("pvo", pvo);
		model.addAttribute("encoded_Photo", encoded_Photo);		
		return "manager/itemupdate";
		
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
		Paging sp = new Paging(count, curPage);

		selInfo.put("sp", sp);
		
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
	@RequestMapping("/itemdelete")
	public Map<String, Object> itemdelete(@RequestParam Map<String,Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		int item_No = Integer.parseInt((String)map.get("item_No"));
		if(ms.deleteItem(item_No) == 1) {
			result.put("result", "삭제완료");
			return result;	
		}else {
			result.put("result", "삭제실패");
			return result;
		}
	}
//	@RequestParam(value = "checkArray[]") List<Integer> checkArray
	@ResponseBody
	@RequestMapping("/selectedRelease")
	public String selectedRelease(Model model, @RequestParam(value="mycheck[]") List<Integer> No_List) {
		System.out.println("폼 전송되긴 했다");
		List<ItemVO> itemList = new ArrayList<ItemVO>();
		for(int a : No_List) {
			System.out.println(a);
			itemList.add(ms.getItemInfo(a));
		}
		model.addAttribute("itemList", itemList);
//		List<ItemVO> itemList = new ArrayList<ItemVO>();
//		for(int a :checkArray) {
//			System.out.println(a);
//			itemList.add(ms.getItemInfo(a));
//		}
		
//		model.addAttribute("itemList", itemList);
		
		return "manager/itemrelease";
	}

	
	@ResponseBody
	@RequestMapping(value="/itemstored", method=RequestMethod.POST)
	public Map<String, Object> itemstored(@RequestParam Map<String,Object> map, 
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
		Paging sp = new Paging(count, curPage);

		selInfo.put("sp", sp);
		
		List<ItemVO> list = ms.getItemlist(selInfo);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("sp", sp);
		return result;
	}
	
	@RequestMapping("/viewitemregister")
	public String viewitemregister(Model model) {
		
		return "manager/itemregister";
		
	}


	@RequestMapping(value = "/viewitemrelease", method = RequestMethod.GET)
	public String viewitemrelease(Model model, @RequestParam(value="item_No_List", required=false) String item_No_List, ItemVO ivo, PhotoVO pvo) {
		
		String[] item_No_Array = item_No_List.split(":");
		List<ItemVO> itemList = new ArrayList<ItemVO>();
		for(String s: item_No_Array) {
			itemList.add(ms.getItemInfo(Integer.parseInt(s)));
		}
		model.addAttribute("itemList", itemList);
		
		return "manager/itemrelease";
	}
	
	@ResponseBody
	@RequestMapping("/itemrelease.do")
	public String itemrelease(@RequestParam(value = "item_No_List[]") List<Integer> item_No_List,
								@RequestParam(value = "amount_List[]") List<Integer> amount_List, 
								@RequestParam(value = "rel_Amount_List[]") List<Integer> rel_Amount_List,
								@RequestParam(value = "store_No") int store_No){
	
		System.out.println("itemrelease.do 왔다!");
		System.out.println("넘어온 매장번호" + store_No);

		Map<String, Object> tempMap = null;
		for(int i = 0 ; i< item_No_List.size() ; i++) {
			tempMap = new HashMap<String, Object>();
			tempMap.put("item_No", item_No_List.get(i));
			tempMap.put("amount", amount_List.get(i) - rel_Amount_List.get(i));
			tempMap.put("store_No", store_No);
			tempMap.put("rel_amount", rel_Amount_List.get(i));
			ms.releaseItem(tempMap);
			ms.storeItem(tempMap);
		}	
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/itemregister.do")
	public Map<String, Object> itemregister(MultipartHttpServletRequest multi) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String checkStr	 = multi.getParameter("company") +multi.getParameter("category") + multi.getParameter("registerNum");
		int checkNum = Integer.parseInt(checkStr);
		
		if(ms.checkregiNum(checkNum) == 0) {
			result.put("check", "입고등록");
			
			ItemVO ivo = new ItemVO();
			ivo.setItem_No(checkNum);  //여기 왜 널이지??
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

			return result;
			
		}else {
			result.put("check", "입고번호 중복");
			return result;
		}

	}
	
	@ResponseBody
	@RequestMapping("/itemupdate.do")
	public Map<String, Object> itemupdate(MultipartHttpServletRequest multi) {
		Map<String, Object> result = new HashMap<String, Object>();

		String checkStr	 = multi.getParameter("company") +multi.getParameter("category") + multi.getParameter("registerNum");
		int checkNum = Integer.parseInt(checkStr);
		int ex_Item_No = Integer.parseInt(multi.getParameter("ex_Item_No"));

		if(checkNum == ex_Item_No) {
			result.put("check", "입고수정");
			Map<String, Object> itemInfo = new HashMap<String, Object>();
			
			ItemVO ivo = new ItemVO();
			ivo.setItem_No(checkNum);  
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
			itemInfo.put("ivo", ivo);
			itemInfo.put("ex_Item_No", ex_Item_No);
			
			ms.updateItem(itemInfo);
			
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
			itemInfo.put("pvo", pvo);
			ms.updatePhoto(itemInfo);

			return result;
			
		}else {

			if(ms.checkregiNum(checkNum) == 0) {
				
				result.put("check", "입고수정");
				
				Map<String, Object> itemInfo = new HashMap<String, Object>();
				
				ItemVO ivo = new ItemVO();
				ivo.setItem_No(checkNum);  
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
				itemInfo.put("ivo", ivo);
				itemInfo.put("ex_Item_No", ex_Item_No);
				
				ms.updateItem(itemInfo);
				
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
				itemInfo.put("pvo", pvo);
				ms.updatePhoto(itemInfo);

				return result;
				
			}else {
				result.put("check", "입고번호 중복");
				return result;
			}
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
	@RequestMapping(value="/storeSel", method = RequestMethod.POST)
	public List<ItemVO> storeSel(Model model){
			
			return ms.storeSel();

	}
	
}


