package com.goods.app.service;

import java.util.List;
import java.util.Map;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.M_boardVO;
import com.goods.app.vo.ManagerVO;
import com.goods.app.vo.PhotoVO;
import com.goods.app.vo.SPostVO;
import com.goods.app.vo.UserVO;


public interface ManagerService {

	public int insertSPostComent(int spost_No, String coment, String manager_Id);
	
	public List<SPostVO> selectSPost();
	
	public List<SPostVO> selectSPost(int curPage);
	
	public int SPostCount();
	
	public int releaseItem(Map<String, Object> map);
	
	public int storeItem(Map<String, Object> map);
	
	public int registerPhoto(PhotoVO pvo);
	
	public List<ItemVO> selectList();
	
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
	
	public List<ItemVO> storeSel();

	public ManagerVO checkManager(String manager_id, String manager_pw) throws Exception;

	public void delete(String user_id);

	public List<M_boardVO> getBoardlist();

	public void deleteBoard(int board_no);

	public void insertboard(M_boardVO vo);

	public M_boardVO selectboard(int board_no);

	public void updateform(M_boardVO vo);

	public List<M_boardVO> getBoardlist2();

}
