package com.goods.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goods.app.dao.UserDAO;
import com.goods.app.vo.UserVO;

@Service
public class UserServiceImpl {
	@Autowired
	UserDAO dao;

	public UserVO checkUser(String id, String pw) throws Exception {
		UserVO vo = new UserVO();
		vo.setUser_id(id);
		vo.setUser_pw(pw);
		return dao.login(vo);
	}
}
