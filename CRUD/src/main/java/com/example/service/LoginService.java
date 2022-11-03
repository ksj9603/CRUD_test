package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.vo.UserVO;

import javax.transaction.Transactional;

@Service
@Transactional
public class LoginService {
	@Autowired
	UserMapper userMapper;
	
	public boolean checkLogin(UserVO userVO) {
		boolean check = userMapper.checkLogin(userVO);
		
		return check;
	}
}
