package com.goods.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goods.app.dao.TestDAO;
import com.goods.app.vo.TestVO;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestDAO tdao;
	
	
	@Override
	public void insert(TestVO vo) {
		// TODO Auto-generated method stub
		tdao.insert(vo);
	}

}
