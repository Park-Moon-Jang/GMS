package com.goods.app.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goods.app.vo.ItemVO;

@Repository
public class ManagerDAO {

	@Autowired
	SqlSession ss;
	
	public void insert(ItemVO vo) {
		System.out.println("dao!");
		ss.insert("insertItem", vo);
	}

	public List<ItemVO> getList() {
		// TODO Auto-generated method stub
		
		//신상 3개만 출력
		return ss.selectList("newitemList");
	}
	
	public int delete(ItemVO vo) {
		System.out.println("dao!");
		return ss.delete("deleteOne", vo);
	}
}
