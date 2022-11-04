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
	public void delBoard(int board_no, String account_id) {
		userMapper.delBoard(board_no, account_id);
	}
	
	public boolean checkBoard(String board_no, String account_id) {
		boolean check = userMapper.checkBoard(board_no, account_id);
		
		return check; 
	}
	
}