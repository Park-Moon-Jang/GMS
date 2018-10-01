package com.goods.app.vo;

import java.util.Date;

public class SPostVO {
	private int spost_No;
	private int category_No;
	private String category_Name;
	private String title;
	private String content;
	private String user_Id;
	private String user_NickName;
	private int hits;
	private int secret;
	public int getSpost_No() {
		return spost_No;
	}
	public void setSpost_No(int spost_No) {
		this.spost_No = spost_No;
	}
	public int getCategory_No() {
		return category_No;
	}
	public void setCategory_No(int category_No) {
		this.category_No = category_No;
	}
	public String getCategory_Name() {
		return category_Name;
	}
	public void setCategory_Name(String category_Name) {
		this.category_Name = category_Name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getUser_NickName() {
		return user_NickName;
	}
	public void setUser_NickName(String user_NickName) {
		this.user_NickName = user_NickName;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getSecret() {
		return secret;
	}
	public void setSecret(int secret) {
		this.secret = secret;
	}
}
