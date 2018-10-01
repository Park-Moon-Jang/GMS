package com.goods.app.vo;

import java.util.Date;

public class comentVO {
	private int coment_No;
	private String user_Id;
	private String content;
	private Date write_Date;
	private String user_NickName;
	public int getComent_No() {
		return coment_No;
	}
	public void setComent_No(int coment_No) {
		this.coment_No = coment_No;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser_NickName() {
		return user_NickName;
	}
	public void setUser_NickName(String user_NickName) {
		this.user_NickName = user_NickName;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public Date getWrite_Date() {
		return write_Date;
	}
	public void setWrite_Date(Date write_Date) {
		this.write_Date = write_Date;
	}
}
