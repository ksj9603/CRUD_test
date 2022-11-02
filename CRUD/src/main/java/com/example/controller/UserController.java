package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping(value="/account")
	public String account() {
		return "account";
	}
	
	@PostMapping(value="/addUser")
	public String addUser(UserVO userVO){
		try {
		userService.save(userVO);
		} catch(Exception e) {
			
		}
		
		return "/userList";
	}
	
	@PostMapping(value="/getUser")
	public String getUserList(Model model) {
		List<UserVO> userList = userService.getUserList();
		
		model.addAttribute("userList",userList);
		
		return "/success";
	}
	@GetMapping(value="/delete/*")
	public String delUserList(Model model) {
		List<UserVO> userList = userService.getUserList();
		
		model.addAttribute("userList",userList);
		
		return "/success";
	}
}
