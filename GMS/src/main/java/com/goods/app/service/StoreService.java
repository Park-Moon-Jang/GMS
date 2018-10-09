package com.goods.app.service;

import java.util.List;
import java.util.Map;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.M_boardVO;
import com.goods.app.vo.ManagerVO;
import com.goods.app.vo.UserVO;


public interface StoreService {

	public List<ItemVO> selectList();

	public int getStoreCount(Map<String, Object> map);
	
	public List<ItemVO> getStoreItem(Map<String, Object> map);
	
	public int returnItem(Map<String, Object> map);

	public int restedItem(Map<String, Object> map);
	
	public ItemVO getStoreItemInfo(int item_No);
}
