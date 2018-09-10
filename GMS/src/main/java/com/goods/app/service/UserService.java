package com.goods.app.service;



import org.springframework.stereotype.Service;

import com.goods.app.vo.UserVO;

@Service
public interface UserService 
{
	public UserVO checkUser(String user_id,String user_pw) throws Exception;
	public UserVO findID(String user_name,String user_email)throws Exception;
	public UserVO findPW(String user_id,String user_email)throws Exception;
	public void join(UserVO vo) throws Exception;
	public void update(UserVO vo) throws Exception;
	public void updatePW(UserVO vo) throws Exception;
	public void delete(UserVO vo)throws Exception ;
	
}
