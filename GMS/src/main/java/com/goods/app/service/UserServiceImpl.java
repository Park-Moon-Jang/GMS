package com.goods.app.service;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goods.app.dao.UserDAO;
import com.goods.app.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO dao;

	@Override
	public UserVO checkUser(String user_id, String user_pw) throws Exception {
		
		UserVO vo = new UserVO();
		vo.setUser_id(user_id);
		vo.setUser_pw(user_pw);
		return dao.login(vo);
	}

	@Override
	public void join(UserVO vo) throws Exception {
		dao.join(vo);
		
	}

	@Override
	public void update(UserVO vo) throws Exception {
		dao.update(vo);
		

	}

	@Override
	public UserVO findID(String user_name, String user_email) throws Exception {
		UserVO vo = new UserVO();
		vo.setUser_name(user_name);
		vo.setUser_email(user_email);
		return dao.findID(vo);
	}

	@Override
	public UserVO findPW(String user_id, String user_email) throws Exception {
		UserVO vo = new UserVO();
		vo.setUser_name(user_id);
		vo.setUser_email(user_email);
		return dao.findPW(vo);
	}

	@Override
	public void updatePW(UserVO vo) throws Exception 
	{
		
		dao.updatePW(vo);
	}

	@Override
	public void delete(UserVO vo) throws Exception {
		dao.delete(vo);
		
	}
		
}



