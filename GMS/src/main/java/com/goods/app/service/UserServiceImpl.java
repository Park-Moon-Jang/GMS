package com.goods.app.service;

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
		System.out.println(vo.getUser_id());
		System.out.println(vo.getUser_pw());
		return dao.login(vo);
	}

	@Override
	public void join(UserVO vo) throws Exception {
		dao.join(vo);
		
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



