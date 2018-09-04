package com.goods.app.service;

import java.util.List;

import com.goods.app.vo.ItemVO;
import com.goods.app.vo.ManagerVO;


public interface ManagerService {

	public List<ItemVO> getItemlist();

	public ManagerVO checkManager(String manager_id, String manager_pw) throws Exception;
}
