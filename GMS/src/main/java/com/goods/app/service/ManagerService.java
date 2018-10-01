package com.goods.app.service;

import java.util.List;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.ManagerVO;
import com.goods.app.vo.UserVO;


public interface ManagerService {

	public List<ItemVO> getItemlist();
	public List<UserVO> getUserlist();

	public ManagerVO checkManager(String manager_id, String manager_pw) throws Exception;

	public void delete(String user_id);



}
