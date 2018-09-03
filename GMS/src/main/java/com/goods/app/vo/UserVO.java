package com.goods.app.vo;


public class UserVO 
{
	private String user_id;
	private String user_pw;
	private String user_nickname;
	private String user_name;
	private int user_phon;
	private String user_email;
	private int birth;
	
	public UserVO() {}
	
	public UserVO(String user_id,String user_pw)
	{
		super();
		this.user_id=user_id;
		this.user_pw=user_pw;
	}
	public UserVO(String user_id,String user_pw,String user_nickname,String user_name,int user_phon,String user_email,int birth)
	{
		super();
		this.user_id=user_id;
		this.user_pw=user_pw;
		this.user_nickname=user_nickname;
		this.user_name=user_name;
		this.user_phon=user_phon;
		this.user_email=user_email;
		this.birth=birth;
	}
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public int getUser_phon() {
		return user_phon;
	}
	public void setUser_phon(int user_phon) {
		this.user_phon = user_phon;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
