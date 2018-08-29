package com.goods.app.service;

import java.util.List;

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


	@Override
	public List<TestVO> getList() {
		// TODO Auto-generated method stub
		return tdao.getList();
	}


	@Override
	public int delete(int category_no) {
		// TODO Auto-generated method stub
		
		
		
		return tdao.delete(category_no);
	}

}
