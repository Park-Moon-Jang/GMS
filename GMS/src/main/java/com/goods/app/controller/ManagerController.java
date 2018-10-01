package com.goods.app.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import com.goods.app.service.ManagerService;
import com.goods.app.vo.ItemVO;
import com.goods.app.vo.Paging;
import com.goods.app.vo.PhotoVO;
import com.goods.app.vo.UserVO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	MappingJackson2JsonView jsonView;
	
	
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
		
		System.out.println("넘어온 아이템번호"+item_No);
		
		ivo = ms.getItemInfo(item_No);
		
		System.out.println("가져온 아이템 이름"+ivo.getItem_Name());
		System.out.println("가져온 아이템 이름 타입"+ivo.getItem_Name().getClass());
		System.out.println("가져온 아이템 이름 길이"+ivo.getItem_Name().length());
		pvo = ms.getItemPhoto(item_No);
		
		String encoded_Photo = Base64.encode(pvo.getPhoto_Data());
		
		model.addAttribute("ivo", ivo);
		model.addAttribute("pvo", pvo);
		model.addAttribute("encoded_Photo", encoded_Photo);
		System.out.println(pvo.getPhoto_Data());
		System.out.println(pvo.getPhoto_Data().getClass());
		
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
		System.out.println("카운트: " + count);
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
	@RequestMapping("/itemdelete")
	public String itemdelete(@RequestParam Map<String,Object> map) {
		System.out.println("itemdelete"+map.get("item_No"));
		int item_No = Integer.parseInt((String)map.get("item_No"));
		System.out.println("delete온 번호: "+item_No);
		if(ms.deleteItem(item_No) == 1) {
			
			System.out.println("삭제됐다");
			return "삭제완료";	
		}else {
			System.out.println("삭제 안 됐다");
			return "삭제실패";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/itemstored", method=RequestMethod.POST)
	public Map<String, Object> itemstored(@RequestParam Map<String,Object> map, 
										ItemVO vo, 
										@RequestParam("from_Date") java.sql.Date from_Date, 
										@RequestParam("to_Date") java.sql.Date to_Date, 
										@RequestParam(defaultValue = "1") int curPage) {
		
		System.out.println("현재 페이지 :"+curPage);
		
		Map<String, Object> selInfo = new HashMap<String, Object>();
		
		selInfo.put("item_Name", vo.getItem_Name());
		selInfo.put("company_No", vo.getCompany_No());
		selInfo.put("category_No", vo.getCategory_No());
		selInfo.put("from_Date", from_Date);
		selInfo.put("to_Date", to_Date);
		
		int count = ms.getCount(selInfo);
		System.out.println("카운드 :"+ count);
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
		
		System.out.println("itemregister.do"+multi.getParameter("item_Name"));
		System.out.println("itemregister.do"+multi.getParameter("item_Name").getClass());
		System.out.println(checkNum);
		
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
			System.out.println("pvo 성공");

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
		
		System.out.println("이전 입고번호"+ ex_Item_No);
		System.out.println("지금 입고번호"+ checkNum);
		
		if(checkNum == ex_Item_No) {
			result.put("check", "입고수정");
			System.out.println("번호가 같음");
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
			System.out.println("pvo 성공");

			return result;
			
		}else {
			System.out.println("번호가 다름");
			if(ms.checkregiNum(checkNum) == 0) {
				
				System.out.println("번호는 다르고 새로운 번호 입고가능");
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
				System.out.println("ivo 성공");
				
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
				System.out.println("pvo 성공");

				return result;
				
			}else {
				System.out.println("번호는 다르고 새로운 번호 입고불가능");
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


	@RequestMapping("/managerboard")
	public ModelAndView mboard (Model model) {
	
		List<ItemVO> itemlist = ms.getnewItemlist();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", itemlist);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("manager/managerboard");  
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
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		return "home";
	}

	@RequestMapping(value="/deleteUser/{user_id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable("user_id") String user_id) {
		ms.delete(user_id);
		return "redirect:/manager/viewmanageuser";
	}

	@RequestMapping("/managerhome")
	public String managerhome (Model model, HttpSession session) {
	
		
		List<ItemVO> itemlist = ms.getnewItemlist();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", itemlist);
		model.addAttribute("itemlist", itemlist);
		
		return "manager/managerhome";
		
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
	
}


