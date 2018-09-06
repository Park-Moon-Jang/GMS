package com.goods.app.dao;





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
		map.put("item_No", company_no+""+category_No);
		map.put("category_No", String.valueOf(category_No));
		map.put("store_Name", store_Name);
		
		return ss.selectList("SelectBtn",map);
	}
	
	
}