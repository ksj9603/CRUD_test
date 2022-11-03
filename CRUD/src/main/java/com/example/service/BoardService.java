package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.vo.BoardVO;

@Service
@Transactional
public class BoardService {

	@Autowired
	UserMapper userMapper;
	
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = userMapper.getBoardList();
		
		return boardList;
	}
	
	public void BoardUpload(BoardVO boardVO) {
		userMapper.boardUpload(boardVO);
	}
}
