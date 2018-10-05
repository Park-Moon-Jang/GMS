package com.goods.app.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.PhotoVO;
import com.goods.app.vo.SComentVO;
import com.goods.app.vo.SPostVO;
import com.goods.app.vo.UserVO;
import com.goods.app.vo.comentVO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Repository
public class UserDAO 
{
	@Autowired
	SqlSession ss;
	 
	public List<ItemVO> storeSel(){
		return ss.selectList("SelectStore");
	}
	
	public List<ItemVO> categorySel(){
		return ss.selectList("SelectCategory");
	}
	
	public List<ItemVO> brandSel(){
		return ss.selectList("SelectBrand");
	}
	
	public void join(UserVO vo)throws Exception
	{
		ss.insert("joinUser",vo);
	}

	public UserVO login(UserVO vo) throws Exception {
		return ss.selectOne("checkUser",vo);
	}

	
	public UserVO findID(UserVO vo) throws Exception
	{
		
		return ss.selectOne("findID",vo);
	}
	

	public UserVO findPW(UserVO vo)throws Exception
	{
		return ss.selectOne("findPW",vo);
	}
	public void updatePW(UserVO vo)
	{

		ss.update("updatePW",vo);
	}

	public void delete(UserVO vo) {
		ss.delete("deleteUser_",vo);
	}
	public void update(UserVO vo) {
		
		ss.update("updateUser_",vo);
	}
	

	public List<ItemVO> selBtn(int company_no,int category_No, String store_Name, int curPage){
		Map map = new HashMap();
		if(company_no != 0 && category_No != 0) {
			map.put("item_No", company_no+""+category_No);
		}else if(company_no != 0 && category_No ==0) {
			map.put("item_No", company_no);
		}else {
			map.put("item_No", category_No);
		}
		map.put("company_No", company_no);
		map.put("category_No", category_No);
		map.put("store_Name", store_Name);
		
		curPage = (curPage - 1 ) * 10;
		map.put("curPage", curPage);
		
		return ss.selectList("SelectBtn",map);
	}
	
	public int selectCount(ItemVO IVO) {
		
		Map map = new HashMap();
		if(IVO.getCompany_No() != 0 && IVO.getCategory_No() != 0) {
			map.put("item_No", IVO.getCompany_No()+""+IVO.getCategory_No());
		}else if(IVO.getCompany_No() != 0 && IVO.getCategory_No() ==0) {
			map.put("item_No", IVO.getCompany_No());
		}else {
			map.put("item_No", IVO.getCategory_No());
		}
		map.put("company_No", IVO.getCompany_No());
		map.put("category_No", IVO.getCategory_No());
		map.put("store_Name", IVO.getStore_Name());
		
	return ss.selectOne("countSelectBtn",IVO);
	}
	
	public List<ItemVO> itemDetalSel(int item_No){
		return ss.selectList("SelectItemDetal", item_No);
	}


	public int checkid(String user_id) {
		return ss.selectOne("checkid",user_id);
	}

	
	

	
	public boolean selectScrap(int item_No, String user_Id) {
		Map map = new HashMap();
		map.put("item_No", item_No);
		map.put("user_Id", user_Id);
		
		if(ss.selectOne("ChoiceScrap",map)) {
			return true;
		}else {
			return false;
		}
	}
	
	public int insertScrap(String user_Id, int item_No) {
		Map map = new HashMap();
		map.put("item_No", item_No);
		map.put("user_Id", user_Id);
		
		return ss.insert("insertScrap", map);
	}

	public int deleteScrap(String user_Id, int item_No) {
		Map map = new HashMap();
		map.put("item_No", item_No);
		map.put("user_Id", user_Id);
		
		return ss.delete("deleteScrap", map);
	}
	public int myScrapCount(String user_Id) {
		
		return ss.selectOne("MyScrapCount",user_Id);
	}
	public List<ItemVO> myScrapSel(String user_Id, int curPage){
		Map map = new HashMap();
		curPage = (curPage - 1 ) * 10;
		map.put("curPage", curPage);
		map.put("user_Id", user_Id);
		
		return ss.selectList("SelectMyScrap",map);
	}


	public void delete_scr(String user_id) {
		ss.delete("deleteUser_scr", user_id);
		
	}
	public int selectedScrapDelete(List<String> checkArray, String user_Id) {
		Map map = new HashMap();
		map.put("user_Id",user_Id);
		map.put("checkArray", checkArray);
		return ss.delete("SelectedScrapDelete", map);

	}
	
	public int insertComent(int item_No, String user_Id, String coment) {
		Map map = new HashMap();
		map.put("item_No", item_No);
		map.put("user_Id",user_Id);
		map.put("coment", coment);
		
		return ss.insert("InsertComent",map);
	}
	
	public List<comentVO> selectComent(int item_No, int curPage){
		Map map = new HashMap();
		curPage = (curPage - 1 ) * 10;
		map.put("item_No", item_No);
		map.put("curPage", curPage);
		return ss.selectList("SelectComent",map);
	}
	
	public int selectComentCount(int item_No) {
		System.out.println(item_No);
		return ss.selectOne("SelectComentCount", item_No);
	}
	
	public int deleteComent(int item_No, int coment_No) {
		Map map = new HashMap();
		map.put("item_No", item_No);
		map.put("coment_No", coment_No);
		return ss.delete("DeleteComent",map);
	}
	
	public int insertSuggestionsPost(Map map) {
		
		return ss.insert("insertSuggestionsPost",map);
	}
	
	public int updateSuggestionsPost(Map map) {
		return ss.update("updateSuggestionsPost",map);
	}
	
	public int selectSPostCount() {
		
		return ss.selectOne("SelectSPostCount");
	}
	
	public List<SPostVO> selectSPost(int curPage){
		curPage = (curPage - 1 ) * 10;
		return ss.selectList("SelectSPost", curPage);
	}
	
	public int updateHits(int spost_no) {
		return ss.update("updateHits",spost_no);
	}

	public List<SPostVO> selDetailSPost(int spost_No){
		return ss.selectList("SelectDetailSPost", spost_No);
	}
	
	public int delSPost(int spost_No) {
		return ss.delete("delSPost", spost_No);
	}
	
	public List<ItemVO> myScrap(String user_Id){
		
		return ss.selectList("MyScrapSelect",user_Id);
	}
	
	public List selPhoto(List<Object> scrapArray){
		Map<String, List<Object>> map = new HashMap();
		map.put("scrapArray", scrapArray);
		List<PhotoVO> PList = ss.selectList("SelectPhoto", map);
		Map<String,Object> resultMap = new HashMap();
		List list = new ArrayList();
		for(PhotoVO e : PList) {
			resultMap.put("item_No",e.getItem_No());
			resultMap.put("photo_Data",Base64.encode(e.getPhoto_Data()));
			list.add(resultMap);
		}
		
		return list;
	}
	public List<SPostVO> mySPost(String user_Id) {
		
		return  ss.selectList("MySPostSelect",user_Id);
	}
	
	public List<SComentVO> selectSPostComent(int spost_No) {
		
		return ss.selectList("SelectSComent",spost_No);
	}
	
	public int delSComnet(int spost_No) {
		return ss.delete("delSComent",spost_No);
	}
	
}