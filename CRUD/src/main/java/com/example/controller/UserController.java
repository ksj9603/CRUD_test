package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public boolean addUser(@RequestBody UserVO userVO, Model model){
		System.out.println(userVO.getId() + userVO.getPwd() + userVO.getName());
		if(userVO.getId() == null) {
			return false;
		}
		boolean check = userService.checkUser(userVO);
		
		if(check == false) {
			userService.save(userVO);
			
		}else {
			return false;
		}
		return true;
	}
	
	@GetMapping(value="/getUser")
	public String getUserList(Model model) {
		List<UserVO> userList = userService.getUserList();
		
		model.addAttribute("userList",userList);
		
		return "/user";
	}
	@GetMapping(value="/delete/*")
	public String delUserList(@RequestParam String lid, Model model) {
		userService.delUser(lid);
		
		List<UserVO> userList = userService.getUserList();
		model.addAttribute("userList",userList);
		
		return "/user";
	}
	
	@GetMapping("/alter")
	public String alterUser(@RequestParam String uid, Model model) {
		List<UserVO> alterUser = userService.alterUser(uid);
		model.addAttribute("alter",alterUser);
		return "/alterUser";
	}
	
	@PostMapping("/changeUser")
	public String changeUser(UserVO userVO, Model model) {
		
		if(userVO.getPwd().equals("") || userVO.getName().equals(""))  {
			
			List<UserVO> userList = userService.getUserList();
			model.addAttribute("userList",userList);
			return "/user";
		}
		
		userService.changeUser(userVO);
		
		List<UserVO> userList = userService.getUserList();
		model.addAttribute("userList", userList);
		
		
		return "/user";
	}
}