package com.goods.app.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.M_boardVO;
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

	public List<ItemVO> getnewItemlist() {
		// TODO Auto-generated method stub
		
		//신상 3개만 출력
		return ss.selectList("getnewItemlist");
	}
	public List<ItemVO> getstoredlist(Map<String, Object> map){
		return ss.selectList("getstoredlist", map);
	}
	
	
	public List<ItemVO> getItemlist(Map<String, Object> map){
		
		return ss.selectList("getItemlist", map); 
		
	}
	public int getCount(Map<String, Object> map){
		
		return ss.selectOne("getCount", map);
	}
	
	public int delete(ItemVO vo) {
		
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

	public List<ItemVO> companySel(){
		return ss.selectList("companySel");
	}	
	public List<ItemVO> categorySel(){
		
		return ss.selectList("categorySel");
	}

	public List<M_boardVO> getboardlist() {
	
		return ss.selectList("boardlist");
	}

	public void deleteBoard(int board_no) {
		// TODO Auto-generated method stub
		ss.delete("deleteBoard",board_no);
	}

	public void insertboard(M_boardVO vo) {
		// TODO Auto-generated method stub
		ss.insert("insertboard", vo);
	}

	public M_boardVO selectboard(int board_no) {
		// TODO Auto-generated method stub
		return ss.selectOne("selectboard", board_no);
	}

}
