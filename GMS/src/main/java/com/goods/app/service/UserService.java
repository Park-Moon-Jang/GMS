package com.goods.app.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.SPostVO;
import com.goods.app.vo.UserVO;
import com.goods.app.vo.comentVO;

@Service
public interface UserService 
{
	public int delSPost(int spost_No);
	public List<SPostVO> selDetailSPost(int spost_No);
	public int updateHits(int spost_no);
	public List<SPostVO> selectSPost(int curPage);
	public int selectSPostCount();
	public int updateSuggestionsPost(Map map);
	public int insertSuggestionsPost(Map map);
	public int deleteComent(int item_No, int coment_No);
	public int selectComentCount(int item_No);
	public List<comentVO> selectComent(int item_No, int curPage);
	public int insertComent(int item_No, String user_Id, String coment);
	public int selectedScrapDelete(List<String> checkArray, String user_Id);
	public int myScrapCount(String user_Id);
	public List<ItemVO> myScrapSel(String user_Id, int curPage);
	public int insertScrap(String user_Id, int item_No);
	public int deleteScrap(String user_Id, int item_No);
	public boolean selectScrap(int item_No, String user_Id);
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
