package com.goods.app.service;



import javax.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goods.app.dao.UserDAO;
import com.goods.app.vo.ItemVO;
import com.goods.app.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO dao;

	@Override
	public UserVO checkUser(String user_id, String user_pw) throws Exception {
		
		UserVO vo = new UserVO();
		vo.setUser_id(user_id);
		vo.setUser_pw(user_pw);
		return dao.login(vo);
	}

	@Override
	public void join(UserVO vo) throws Exception {
		dao.join(vo);
		
	}

	@Override
	public void update(UserVO vo) throws Exception {
		dao.update(vo);
		

	}

	@Override
	public UserVO findID(String user_name, String user_email) throws Exception {
		UserVO vo = new UserVO();
		vo.setUser_name(user_name);
		vo.setUser_email(user_email);
		return dao.findID(vo);
	}

	@Override
	public UserVO findPW(String user_id, String user_email) throws Exception {
		UserVO vo = new UserVO();
		vo.setUser_name(user_id);
		vo.setUser_email(user_email);
		return dao.findPW(vo);
	}

	@Override
	public void updatePW(UserVO vo) throws Exception 
	{
		
		dao.updatePW(vo);
	}
	@Override
	public List<ItemVO> brandSel() {
		// TODO Auto-generated method stub
		return dao.brandSel();
	}

	@Override
	public List<ItemVO> categorySel() {
		// TODO Auto-generated method stub
		return dao.categorySel();
	}

	@Override
	public List<ItemVO> storeSel() {
		// TODO Auto-generated method stub
		return dao.storeSel();
	}

	@Override
	public List<ItemVO> selBtn(int company_No,int category_No, String store_Name, int curPage) {
		// TODO Auto-generated method stub
		return dao.selBtn(company_No,category_No,store_Name,curPage);
	}

	@Override
	public int selectCount(ItemVO IVO) {
		// TODO Auto-generated method stub
		return dao.selectCount(IVO);
	}

	@Override
	public List<ItemVO> itemDetalSel(int item_No) {
		// TODO Auto-generated method stub
		return dao.itemDetalSel(item_No);

	}
		
}



