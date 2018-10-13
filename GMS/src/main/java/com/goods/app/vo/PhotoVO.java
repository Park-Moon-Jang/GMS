package com.goods.app.vo;


public class PhotoVO {
	private int item_No;
	private String item_Name;
	private String photo_Name;
	private byte[] photo_Data;
	private String encoded_Data;
	
	
	public String getEncoded_Data() {
		return encoded_Data;
	}
	public void setEncoded_Data(String encoded_Data) {
		this.encoded_Data = encoded_Data;
	}
	public int getItem_No() {
		return item_No;
	}
	public void setItem_No(int item_No) {
		this.item_No = item_No;
	}
	public String getItem_Name() {
		return item_Name;
	}
	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
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
