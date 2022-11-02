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
}
