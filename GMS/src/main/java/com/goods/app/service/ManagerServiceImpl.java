package com.goods.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goods.app.dao.ManagerDAO;
import com.goods.app.vo.ItemVO;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerDAO mdao;	
	
	@Override
	public List<ItemVO> getItemlist() {
		// TODO Auto-generated method stub
		
		return mdao.getList();
	}

}
