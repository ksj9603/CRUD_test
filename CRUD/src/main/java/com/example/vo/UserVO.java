package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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
}
