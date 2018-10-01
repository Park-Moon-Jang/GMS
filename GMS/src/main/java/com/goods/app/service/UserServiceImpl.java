package com.goods.app.service;



import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goods.app.dao.UserDAO;
import com.goods.app.vo.ItemVO;
import com.goods.app.vo.SPostVO;
import com.goods.app.vo.UserVO;
import com.goods.app.vo.comentVO;

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

	@Override
	public boolean selectScrap(int item_No, String user_Id) {
		// TODO Auto-generated method stub
		return dao.selectScrap(item_No, user_Id);
	}

	@Override
	public int insertScrap(String user_Id, int item_No) {
		// TODO Auto-generated method stub
		return dao.insertScrap(user_Id, item_No);
	}

	@Override
	public int deleteScrap(String user_Id, int item_No) {
		// TODO Auto-generated method stub
		return dao.deleteScrap(user_Id, item_No);
	}

	@Override
	public List<ItemVO> myScrapSel(String user_Id, int curPage) {
		// TODO Auto-generated method stub
		return dao.myScrapSel(user_Id, curPage);
	}

	@Override
	public int myScrapCount(String user_Id) {
		// TODO Auto-generated method stub
		return dao.myScrapCount(user_Id);
	}

	@Override
	public int selectedScrapDelete(List<String> checkArray, String user_Id) {
		// TODO Auto-generated method stub
		return dao.selectedScrapDelete(checkArray, user_Id);
	}

	@Override
	public int insertComent(int item_No, String user_Id, String coment) {
		// TODO Auto-generated method stub
		return dao.insertComent(item_No, user_Id, coment);
	}

	@Override
	public List<comentVO> selectComent(int item_No, int curPage) {
		// TODO Auto-generated method stub
		return dao.selectComent(item_No, curPage);
	}

	@Override
	public int selectComentCount(int item_No) {
		// TODO Auto-generated method stub
		return dao.selectComentCount(item_No);
	}

	@Override
	public int deleteComent(int item_No, int coment_No) {
		// TODO Auto-generated method stub
		return dao.deleteComent(item_No, coment_No);
	}

	@Override
	public int insertSuggestionsPost(Map map) {
		// TODO Auto-generated method stub
		return dao.insertSuggestionsPost(map);
	}
	
	@Override
	public int selectSPostCount() {
		// TODO Auto-generated method stub
		return dao.selectSPostCount();
	}

	@Override
	public List<SPostVO> selectSPost(int curPage) {
		// TODO Auto-generated method stub
		return dao.selectSPost(curPage);
	}

	@Override
	public int updateHits(int spost_no) {
		// TODO Auto-generated method stub
		return dao.updateHits(spost_no);
	}

	@Override
	public List<SPostVO> selDetailSPost(int spost_No) {
		// TODO Auto-generated method stub
		return dao.selDetailSPost(spost_No);
	}

	@Override
	public int updateSuggestionsPost(Map map) {
		// TODO Auto-generated method stub
		return dao.updateSuggestionsPost(map);
	}
	
	@Override
	public int delSPost(int spost_No) {
		return dao.delSPost(spost_No);
	}
}



