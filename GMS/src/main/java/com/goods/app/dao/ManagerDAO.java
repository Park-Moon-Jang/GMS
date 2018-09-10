package com.goods.app.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.ManagerVO;


@Repository
public class ManagerDAO {

	@Autowired
	SqlSession ss;
	
	public void insert(ItemVO vo) {
		System.out.println("dao!");
		ss.insert("insertItem", vo);
	}

	public List<ItemVO> getnewItemlist() {
		// TODO Auto-generated method stub
		
		//신상 3개만 출력
		return ss.selectList("getnewItemlist");
	}
	public List<ItemVO> getItemlist(ItemVO vo){
		return ss.selectList("getItemlist", vo);
	}
	
	
	public int delete(ItemVO vo) {
		System.out.println("dao!");
		return ss.delete("deleteOne", vo);
	}
	public ManagerVO login(ManagerVO vo) throws Exception {
		return ss.selectOne("checkManager",vo);
	} 
	public List<ItemVO> companySel(){
		
		return ss.selectList("companySel");
	}	
	public List<ItemVO> categorySel(){
		
		return ss.selectList("categorySel");
	}
}
