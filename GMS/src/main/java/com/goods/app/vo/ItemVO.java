package com.goods.app.vo;


import java.util.Date;

public class ItemVO {

	private int item_No;
	private String item_Name;
	private int amount;
	private int category_No;
	private int company_No;
	private int price;
	private Date carry_Date;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCategory_No() {
		return category_No;
	}
	public void setCategory_No(int category_No) {
		this.category_No = category_No;
	}
	public int getCompany_No() {
		return company_No;
	}
	public void setCompany_No(int company_No) {
		this.company_No = company_No;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getCarry_Date() {
		return carry_Date;
	}
	public void setCarry_Date(Date carry_Date) {
		this.carry_Date = carry_Date;
	}
	
	


	
}
