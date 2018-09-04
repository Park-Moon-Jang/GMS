package com.goods.app.vo;

import java.util.Date;

public class ItemVO {

	private int item_no;
	private String item_name;
	private int amount;
	private int category_no;
	private int company_no;
	private int price;
	private Date carry_date;
	
	
	public int getItem_no() {
		return item_no;
	}
	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	public int getCompany_no() {
		return company_no;
	}
	public void setCompany_no(int company_no) {
		this.company_no = company_no;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getCarry_date() {
		return carry_date;
	}
	public void setCarry_date(Date carry_date) {
		this.carry_date = carry_date;
	}

	
}
