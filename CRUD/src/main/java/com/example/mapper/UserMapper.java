package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.vo.UserVO;

@Mapper
@Repository
public interface UserMapper {
	
	void save(UserVO userVO);
	
	public List<UserVO> getList();
	
	void delUser(String id);
	
	public boolean checkId(UserVO userVO);
	
	public List<UserVO> alterUser(String id);
	
	public void changeUser(UserVO userVO);
}
