package com.goods.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goods.app.dao.ManagerDAO;
import com.goods.app.vo.ItemVO;
import com.goods.app.vo.ManagerVO;
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

}
