package com.goods.app.dao;





import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.UserVO;

@Repository
public class UserDAO 
{
	@Autowired
	SqlSession ss;
	 
	public List<ItemVO> storeSel(){
		return ss.selectList("SelectStore");
	}
	
	public List<ItemVO> categorySel(){
		return ss.selectList("SelectCategory");
	}
	
	public List<ItemVO> brandSel(){
		return ss.selectList("SelectBrand");
	}
	
	public void join(UserVO vo)throws Exception
	{
		ss.insert("joinUser",vo);
	}

	public UserVO login(UserVO vo) throws Exception {
		return ss.selectOne("checkUser",vo);
	}
	
	public List<ItemVO> selBtn(int company_no,int category_No, String store_Name){
		Map<String,String> map = new HashMap();
		if(company_no != 0 && category_No != 0) {
			map.put("item_No", company_no+""+category_No);
		}else if(company_no == 0 && category_No !=0) {
			map.put("item_No", String.valueOf(category_No));
		}else if(company_no != 0 && category_No ==0) {
			map.put("item_No", String.valueOf(company_no));
		}
		map.put("category_No", String.valueOf(category_No));
		
		if(!store_Name.equals(""))
			map.put("store_Name", store_Name);
		
		List<ItemVO> List = new ArrayList();
		if(!store_Name.equals("") && category_No != 0 && company_no != 0 ) {
			List = ss.selectList("SelectBtn",map);
		}else if(store_Name.equals("") && category_No != 0 && company_no != 0 ) {
			List = ss.selectList("SelectBtn2",map);
		}else if(store_Name.equals("") && category_No == 0 && company_no != 0) {
			List = ss.selectList("SelectBtn3",map);
		}else if(store_Name.equals("") && category_No == 0 && company_no == 0) {
			List = ss.selectList("SelectBtn4",map);
		}else if(store_Name.equals("") && category_No != 0 && company_no == 0) {
			List = ss.selectList("SelectBtn5",map);
		}else if(!store_Name.equals("") && category_No == 0 && company_no == 0) {
			List = ss.selectList("SelectBtn6",map);
		}else if(!store_Name.equals("") && category_No != 0 && company_no == 0) {
			List = ss.selectList("SelectBtn7",map);
		}else {
			List = ss.selectList("SelectBtn8",map);
		}
		
		return List;
	}
	
	
}