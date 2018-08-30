package com.goods.app.service;

import org.springframework.stereotype.Service;

import com.goods.app.vo.UserVO;

@Service
public interface UserService 
{
	public UserVO checkUser(String id,String pw) throws Exception;
	public void join(UserVO vo) throws Exception;
}
