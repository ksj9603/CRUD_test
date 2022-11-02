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
	
	public UserVO() {
		
	}
}
