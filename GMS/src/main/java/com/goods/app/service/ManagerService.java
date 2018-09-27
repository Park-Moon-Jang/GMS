package com.goods.app.service;

import java.util.List;
import java.util.Map;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.M_boardVO;
import com.goods.app.vo.ManagerVO;
import com.goods.app.vo.UserVO;


public interface ManagerService {


	public List<UserVO> getUserlist();

	public List<ItemVO> getnewItemlist();
	
	public List<ItemVO> getstoredlist(Map<String, Object> map);
	
	public List<ItemVO> getItemlist(Map<String, Object> map);
	
	public int getCount(Map<String, Object> map);
	
	public List<ItemVO> companySel();
	
	public List<ItemVO> categorySel();

	public ManagerVO checkManager(String manager_id, String manager_pw) throws Exception;

	public void delete(String user_id);

	public List<M_boardVO> getBoardlist();

	public void deleteBoard(int board_no);

	public void insertboard(M_boardVO vo);

	public M_boardVO selectboard(int board_no);

	public void updateform(M_boardVO vo);

}
