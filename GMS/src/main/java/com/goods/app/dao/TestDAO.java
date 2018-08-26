package com.goods.app.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goods.app.vo.TestVO;

@Repository
public class TestDAO {

	@Autowired
	SqlSession ss;
	
	public void insert(TestVO vo) {
		System.out.println("dao!");
		ss.insert("insert", vo);
	}
	
}
