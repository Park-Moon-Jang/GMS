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
import com.goods.app.vo.PhotoVO;
import com.goods.app.vo.UserVO;


@Repository
public class ManagerDAO {

	@Autowired
	SqlSession ss;

	public int checkregiNum(int checkNum) {
		
		return ss.selectOne("checkregiNum", checkNum);
	}
	public int registerItem(ItemVO ivo) {
		
		return ss.insert("registerItem", ivo);
	}
	public int updateItem(Map<String, Object> map) {

		return ss.update("updateItem", map);
	}
	
	public int registerPhoto(PhotoVO pvo) {
		
		return ss.insert("registerPhoto", pvo);
	}
	
	public ItemVO getItemInfo(int item_No) {
		
		return ss.selectOne("getItemInfo", item_No);
	}
	
	public PhotoVO getItemPhoto(int item_No) {
		
		return ss.selectOne("getItemPhoto", item_No);
	}
	
	public int updatePhoto(Map<String, Object> map) {
		
		return ss.update("updatePhoto", map);
	}
	public int deleteItem(int item_No) {
		
		return ss.delete("deleteItem", item_No);
	}
	public int releaseItem(Map<String, Object> map) {
		
		return ss.update("releaseItem", map);
	}
	public int storeItem(Map<String, Object> map) {
		
		return ss.insert("storeItem", map);
	}
	
	public void insert(ItemVO vo) {
		
		ss.insert("insertItem", vo);
	}

	public List<ItemVO> getnewItemlist() {
		
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

	public void updateboard(M_boardVO vo) {
		ss.update("updateboard",vo);
		
	}
	public List<ItemVO> select() {
		
		return ss.selectList("itemrank");
	}
	public List<M_boardVO> getboardlist2() {
		// TODO Auto-generated method stub
		return ss.selectList("boardlist2");
	}	
	public List<ItemVO> storeSel(){
		
		return ss.selectList("storeSel");

	}

}
