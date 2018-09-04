package com.goods.app.service;

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
		
}



