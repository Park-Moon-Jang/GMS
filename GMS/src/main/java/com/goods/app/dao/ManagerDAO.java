package com.goods.app.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.ManagerVO;
import com.goods.app.vo.UserVO;


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
	public ManagerVO login(ManagerVO vo) throws Exception {
		return ss.selectOne("checkManager",vo);
	}

	public List<UserVO> getUserlist() {
		return ss.selectList("userlist");
	}



	public void deleteUser(String user_id) {
		ss.delete("deleteUser",user_id);
		
	}


}
