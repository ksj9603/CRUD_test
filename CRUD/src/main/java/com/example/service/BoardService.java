package com.example.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.mapper.UserMapper;
import com.example.vo.BoardVO;
import com.example.vo.SearchVO;

@Service
@Transactional
public class BoardService {

	@Autowired
	UserMapper userMapper;
	
	public List<BoardVO> getBoardList(int category) {
		List<BoardVO> boardList = userMapper.getBoardList(category);
		
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
	
	public List<BoardVO> alterBoard(int board_no) {
		List<BoardVO> board = userMapper.alterBoard(board_no);
		
		return board;
	}
	
	public void boardAlter(BoardVO boardVO) {
		userMapper.boardAlter(boardVO);
	}
	
	public List<BoardVO> findAll(HashMap<String, Object> board) {
		List<BoardVO> boardList = userMapper.findAll(board );
		return boardList;
		
	}
	
	public List<BoardVO> searchBoard(HashMap<String,Object> board) {
		System.out.println(board.get("search")+ " search");
		List<BoardVO> boardList = userMapper.searchBoard(board);
		return boardList;
	}
	
	public List<BoardVO> searchAllBoard(HashMap<String,Object> board) {
		List<BoardVO> boardList = userMapper.searchAllBoard(board);
		return boardList;
	}
	
}