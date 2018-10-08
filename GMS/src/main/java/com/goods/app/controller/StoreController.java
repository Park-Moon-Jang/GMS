package com.goods.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goods.app.service.ManagerService;
import com.goods.app.service.StoreService;
import com.goods.app.vo.ItemVO;
import com.goods.app.vo.Paging;
import com.goods.app.vo.PhotoVO;

@Controller
@RequestMapping("/store")
public class StoreController {


	@Autowired
	StoreService ss;

	@RequestMapping(value = "/viewitemreturn", method = RequestMethod.GET)
	public String viewitemreturn(Model model, @RequestParam(value="item_No_List", required=false) String item_No_List, ItemVO ivo) {
		
		String[] item_No_Array = item_No_List.split(":");
		List<ItemVO> itemList = new ArrayList<ItemVO>();
		for(String s: item_No_Array) {
			itemList.add(ss.getStoreItemInfo(Integer.parseInt(s)));
		}
		model.addAttribute("itemList", itemList);
		
		return "manager/itemreturn";

	}	
	
	@RequestMapping("/viewstore")
	public String viewstore (Model model) {
		
		return "manager/storelist";
	}

	@ResponseBody
	@RequestMapping("/itemreturn.do")
	public Map<String, Object> itemreturn(@RequestParam(value = "item_No_List[]") List<Integer> item_No_List,
								@RequestParam(value = "amount_List[]") List<Integer> amount_List, 
								@RequestParam(value = "ret_Amount_List[]") List<Integer> ret_Amount_List){


		Map<String, Object> tempMap = null;
		for(int i = 0 ; i< item_No_List.size() ; i++) {
			tempMap = new HashMap<String, Object>();
			tempMap.put("item_No", item_No_List.get(i));
			tempMap.put("amount", amount_List.get(i) - ret_Amount_List.get(i));   //매장에 남는 수량
			tempMap.put("ret_amount", ret_Amount_List.get(i));   //item 에 더해질 수량
			ss.returnItem(tempMap);  //아이템에 수량 더하기
			ss.restedItem(tempMap);   //매장에 수량 남기기
			
		}	
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("check", "반납이 완료되었습니다");
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/selStoreItem", method=RequestMethod.POST)
	public Map<String, Object> itemlist(@RequestParam Map<String,Object> map, 
										ItemVO vo, 
										@RequestParam("store_No") int store_No,
										@RequestParam("from_Date") java.sql.Date from_Date, 
										@RequestParam("to_Date") java.sql.Date to_Date, 
										@RequestParam(defaultValue = "1") int curPage) {
		
		System.out.println("매장번호:" + store_No +":"+ "시작일:"+from_Date+":" +"종료일:"+ to_Date +":"+"페이지:"+ curPage+
				":"+"item_Name:"+vo.getItem_Name()+":"+"company_No:"+ vo.getCompany_No()+":"+"category_No:"+ vo.getCategory_No());		
		
		Map<String, Object> selInfo = new HashMap<String, Object>();
		
		selInfo.put("item_Name", vo.getItem_Name());
		selInfo.put("company_No", vo.getCompany_No());
		selInfo.put("category_No", vo.getCategory_No());
		selInfo.put("from_Date", from_Date);
		selInfo.put("to_Date", to_Date);
		selInfo.put("store_No", store_No);
		
		int count = ss.getStoreCount(selInfo);
		System.out.println("count는 :"+ count);
		Paging sp = new Paging(count, curPage);

		selInfo.put("sp", sp);
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<ItemVO> list = ss.getStoreItem(selInfo);
		if(list.size() == 0) {
			result.put("check", "조건에 해당하는 상품이 없습니다");
		}else {
			result.put("check", list.size()+" 종류의 상품이 있습니다");
		}
		
		result.put("list", list);
		result.put("sp", sp);
		return result;
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
