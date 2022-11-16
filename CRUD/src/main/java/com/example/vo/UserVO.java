package com.example.vo;

public class UserVO {
	public String id;
	public String pwd;
	public String name;
	public int login_cnt;
	
	public UserVO() {
		
	}
	
	public UserVO(String id) {
		this.id = id;
	}
	
	public UserVO(int login_cnt) {
		this.login_cnt = login_cnt;
	}
	
	public UserVO(String id, String pwd) {
		this.id=id;
		this.pwd=pwd;
	}
	
	public UserVO(String id, String pwd, String name, int login_cnt) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.login_cnt = login_cnt;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLogin_cnt() {
		return login_cnt;
	}

	public void setLogin_cnt(int login_cnt) {
		this.login_cnt = login_cnt;
	}

}
