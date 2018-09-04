package com.goods.app.dao;





import java.util.List;

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
	
	
	
	
}