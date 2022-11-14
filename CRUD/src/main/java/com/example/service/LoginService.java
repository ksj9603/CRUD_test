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
	
	public void cntChange(UserVO userVO) {
		userMapper.cntChange(userVO);
	}
	
	public int checkCntId(UserVO userVO) {
		int check = userMapper.checkCntId(userVO);
		
		return check;
	}
	
	public void lastLogin(UserVO userVO) {
		userMapper.lastLogin(userVO);
	}
}