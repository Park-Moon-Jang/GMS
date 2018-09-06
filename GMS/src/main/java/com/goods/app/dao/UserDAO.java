package com.goods.app.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goods.app.vo.UserVO;

@Repository
public class UserDAO 
{
	@Autowired
	SqlSession ss;
	
	public void join(UserVO vo)throws Exception
	{
		ss.insert("joinUser",vo);
	}

	public UserVO login(UserVO vo) throws Exception {
		return ss.selectOne("checkUser",vo);
	}

	public void update(UserVO vo) {
		
		ss.insert("updateUser",vo);
	}
	public UserVO findID(UserVO vo) throws Exception
	{
		
		return ss.selectOne("findID",vo);
	}
	
	public UserVO findPW(UserVO vo)throws Exception
	{
		return ss.selectOne("findPW",vo);
	}
	public void updatePW(UserVO vo)
	{
		ss.update("updatePW",vo);
	}
	
}