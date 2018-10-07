package com.goods.app.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.UserVO;

@Service
public interface UserService 
{
	public List<ItemVO> itemDetalSel(int item_No);
	public int selectCount(ItemVO IVO);
	public List<ItemVO> selBtn(int company_No,int category_No, String store_Name, int curPage);
	public List<ItemVO> storeSel();
	public List<ItemVO> brandSel();
	public List<ItemVO> categorySel();
	public UserVO checkUser(String user_id,String user_pw) throws Exception;
	public UserVO findID(String user_name,String user_email)throws Exception;
	public UserVO findPW(String user_id,String user_email)throws Exception;
	public void join(UserVO vo) throws Exception;
	public void update(UserVO vo) throws Exception;
	public void updatePW(UserVO vo) throws Exception;
	
}
