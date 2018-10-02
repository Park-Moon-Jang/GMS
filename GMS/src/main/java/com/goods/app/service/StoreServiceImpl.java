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
	
	

}
