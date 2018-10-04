package com.goods.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goods.app.dao.ManagerDAO;
import com.goods.app.vo.ItemVO;
import com.goods.app.vo.M_boardVO;
import com.goods.app.vo.ManagerVO;
import com.goods.app.vo.PhotoVO;
import com.goods.app.vo.SPostVO;
import com.goods.app.vo.UserVO;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerDAO mdao;	
	
	@Override
	public List<ItemVO> getnewItemlist() {
		// TODO Auto-generated method stub
		
		return mdao.getnewItemlist();
	}
	
	@Override
	public int getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mdao.getCount(map);
	}
	@Override
	public List<ItemVO> getItemlist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		return mdao.getItemlist(map);
	}

	@Override
	public ManagerVO checkManager(String manager_id, String manager_pw) throws Exception {
		ManagerVO vo = new ManagerVO();
		vo.setManager_id(manager_id);
		vo.setManager_pw(manager_pw);
		
		return mdao.login(vo);
	}

	@Override
	public List<UserVO> getUserlist() {
		// TODO Auto-generated method stub
		return mdao.getUserlist();
	}



	@Override
	public void delete(String user_id) {
		mdao.deleteUser(user_id);
		
	}

	public List<ItemVO> companySel() {
		// TODO Auto-generated method stub
				
		return mdao.companySel();
	}

	@Override
	public List<ItemVO> categorySel() {
		// TODO Auto-generated method stub
		return mdao.categorySel();
	}

	@Override
	public List<ItemVO> getstoredlist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mdao.getstoredlist(map);
	}

	@Override
	public List<M_boardVO> getBoardlist() {
		// TODO Auto-generated method stub
		return mdao.getboardlist();
	}

	@Override
	public void deleteBoard(int board_no) {
		// TODO Auto-generated method stub
		mdao.deleteBoard(board_no);
	}

	@Override
	public void insertboard(M_boardVO vo) {
		// TODO Auto-generated method stub
		mdao.insertboard(vo);
	}

	@Override
	public M_boardVO selectboard(int board_no) {
		return mdao.selectboard(board_no);
	}

	@Override
	public void updateform(M_boardVO vo) {
		mdao.updateboard(vo);
	}
	@Override
	public int checkregiNum(int checkNum) {
		// TODO Auto-generated method stub
		return mdao.checkregiNum(checkNum);
	}

	@Override
	public int registerItem(ItemVO ivo) {
		// TODO Auto-generated method stub
		return mdao.registerItem(ivo);
	}

	@Override
	public int registerPhoto(PhotoVO pvo) {
		// TODO Auto-generated method stub
		return mdao.registerPhoto(pvo);

	}

	@Override
	public List<ItemVO> selectList() {
		
		return mdao.select();
	}

	@Override
	public List<M_boardVO> getBoardlist2() {
		// TODO Auto-generated method stub
		return mdao.getboardlist2();
	}

	@Override
	public List<SPostVO> selectSPost(int curPage) {
		// TODO Auto-generated method stub
		return mdao.selectSPost(curPage);
	}
	
	@Override
	public List<SPostVO> selectSPost() {
		// TODO Auto-generated method stub
		return mdao.selectSPost();
	}

	@Override
	public int SPostCount() {
		// TODO Auto-generated method stub
		return mdao.SPostCount();
	}

	@Override
	public int insertSPostComent(int spost_No, String coment, String manager_Id) {
		// TODO Auto-generated method stub
		return mdao.insertSPostComent(spost_No,coment,manager_Id);
	}

}
