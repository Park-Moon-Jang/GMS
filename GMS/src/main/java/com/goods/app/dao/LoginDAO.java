package com.goods.app.dao;

import java.util.List;

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

	public List<TestVO> getList() {
		// TODO Auto-generated method stub
		
		return ss.selectList("tListAll");
	}
	
	public int delete(int category_no) {
		System.out.println("dao!");
		return ss.delete("deleteOne", category_no);
	}
}
