package com.goods.app.vo;

import java.util.List;


public class PhotoVO {

	private int item_No;
	private String photo_Name;
	private byte[] photo_Data;
	
	
	public int getItem_No() {
		return item_No;
	}
	public void setItem_No(int item_No) {
		this.item_No = item_No;
	}
	public String getPhoto_Name() {
		return photo_Name;
	}
	public void setPhoto_Name(String photo_Name) {
		this.photo_Name = photo_Name;
	}
	public byte[] getPhoto_Data() {
		return photo_Data;
	}
	public void setPhoto_Data(byte[] photo_Data) {
		this.photo_Data = photo_Data;
	}


	
}
