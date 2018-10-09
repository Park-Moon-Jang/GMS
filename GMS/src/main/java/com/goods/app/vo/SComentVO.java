package com.goods.app.vo;

import java.util.Date;

public class SComentVO {

	private int coment_No;
	private int spost_No;
	private String content;
	private String manager_Id;
	private Date write_Date;
	public int getComent_No() {
		return coment_No;
	}
	public void setComent_No(int coment_No) {
		this.coment_No = coment_No;
	}
	public int getSpost_No() {
		return spost_No;
	}
	public void setSpost_No(int spost_No) {
		this.spost_No = spost_No;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getManager_Id() {
		return manager_Id;
	}
	public void setManager_Id(String manager_Id) {
		this.manager_Id = manager_Id;
	}
	public Date getWrite_Date() {
		return write_Date;
	}
	public void setWrite_Date(Date write_Date) {
		this.write_Date = write_Date;
	}
	
	
}
