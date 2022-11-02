package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.service.UserService;
import com.example.vo.UserVO;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/account")
	public String account() {
		return "account";
	}
	
	@PostMapping("/addUser")
	public String addUser(UserVO userVO){
		try {
		userService.save(userVO);
		} catch(Exception e) {
			
		}
		return "/success";
	}
}
