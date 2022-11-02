package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.vo.UserVO;

@Mapper
@Repository
public interface PostMapper {
	
	void save(UserVO userVO);
}
