package com.goods.app.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

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
	
	public List<ItemVO> getItemlist(Map<String, Object> map){
		
//		int comNo = (Integer) map.get("company_No");
//		int cateNo = (Integer) map.get("category_No");
//		System.out.println(comNo +" : " + cateNo);
		return ss.selectList("getItemlist", map); 
		
//		if(comNo == 0 || cateNo == 0) {                       // 둘중 하나라도 0인 경우
//			
//			if( comNo==0 && cateNo== 0) {                     //둘다 0인 경우
//				return ss.selectList("getItemlistNOT", map);
//			}else if(comNo == 0) {                            //com은 0, cate 는 0이 아닌 경우
//				return ss.selectList("getItemlistNOTCOMP", map);
//			}else {                                           //cate 는 0, com은 0이 아닌 경우
//				return ss.selectList("getItemlistNOTCATE", map);
//			}
//		}else {                                               // 둘다 0이 아닌 경우
//			return ss.selectList("getItemlist", map);  
//		}
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
