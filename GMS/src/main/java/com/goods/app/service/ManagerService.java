package com.goods.app.service;

import java.util.List;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.ManagerVO;


public interface ManagerService {

	public List<ItemVO> getnewItemlist();
	
	public List<ItemVO> getItemlist(ItemVO vo);
	
	public List<ItemVO> companySel();
	
	public List<ItemVO> categorySel();
	public ManagerVO checkManager(String manager_id, String manager_pw) throws Exception;
	
	
}
