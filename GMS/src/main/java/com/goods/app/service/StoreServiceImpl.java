package com.goods.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goods.app.dao.StoreDAO;
import com.goods.app.vo.ItemVO;



@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreDAO sdao;

	@Override
	public List<ItemVO> selectList() {
		return sdao.select();
		
	}

	@Override
	public int getStoreCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sdao.getStoreCount(map);
	}

	@Override
	public List<ItemVO> getStoreItem(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sdao.getStoreItem(map);
	}

	@Override
	public int returnItem(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sdao.returnItem(map);
	}

	@Override
	public int restedItem(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sdao.restedItem(map);
	}

	@Override
	public ItemVO getStoreItemInfo(int item_No) {
		// TODO Auto-generated method stub
		return sdao.getStoreItemInfo(item_No);
	}
	
	

}
