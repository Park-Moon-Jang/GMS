package com.goods.app.service;

import java.util.List;
import java.util.Map;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.ManagerVO;
import com.goods.app.vo.PhotoVO;
import com.goods.app.vo.UserVO;


public interface ManagerService {

	public int registerPhoto(PhotoVO pvo);
	
	public int registerItem(ItemVO ivo);
	
	public int updateItem(Map<String, Object> map);
	
	public int updatePhoto(Map<String, Object> map);
	
	public int deleteItem(int item_No);
	
	public int checkregiNum(int checkNum);
	
	public ItemVO getItemInfo(int item_No);
	
	public PhotoVO getItemPhoto(int item_No);
	
	public List<UserVO> getUserlist();

	public List<ItemVO> getnewItemlist();
	
	public List<ItemVO> getstoredlist(Map<String, Object> map);
	
	public List<ItemVO> getItemlist(Map<String, Object> map);
	
	public int getCount(Map<String, Object> map);
	
	public List<ItemVO> companySel();
	
	public List<ItemVO> categorySel();

	public ManagerVO checkManager(String manager_id, String manager_pw) throws Exception;

	public void delete(String user_id);


}
