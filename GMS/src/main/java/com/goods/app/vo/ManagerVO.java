package com.goods.app.vo;

public class ManagerVO {
	private String manager_id;

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public String getManager_pw() {
		return manager_pw;
	}

	public void setManager_pw(String manager_pw) {
		this.manager_pw = manager_pw;
	}

	private String manager_pw;

	public ManagerVO() {
	}

	public ManagerVO(String manager_id, String manager_pw) {
		super();
		this.manager_id = manager_id;
		this.manager_pw = manager_pw;
	}

}
