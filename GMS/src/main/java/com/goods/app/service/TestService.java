package com.goods.app.service;

import java.util.List;

import com.goods.app.vo.TestVO;

public interface TestService {

	public void insert(TestVO vo);
	public List<TestVO> getList();
	public int delete(int category_no);
}
