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
public class StoreDAO {

	@Autowired
	SqlSession ss;

	public List<ItemVO> select() {
		
		return ss.selectList("itemrank");
	}
	
	

}
