package com.example.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.vo.BoardVO;
import com.example.vo.CommentVO;

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
	
	public List<BoardVO> boardInfoData(int board_no1) {
		List<BoardVO> board = userMapper.boardInfoData(board_no1);
		
		return board;
	}
	
	public void boardLikeHate(HashMap<String,Object> hash) {
		userMapper.boardLikeHate(hash) ;
		
	}
	
	public int thumbCheck(String account_id) {
		int count = userMapper.thumbCheck(account_id);
		
		return count;
	}
	
	public void thumbCnt(String account_id) {
		userMapper.thumbCnt(account_id);
	}
	
	public boolean thumbTime(String account_id) {
		boolean check = userMapper.thumbTime(account_id);
		
		return check;
	}
	
	public void thumbTimeChange(String account_id) {
		userMapper.thumbTimeChange(account_id);
	}
	public void comment(HashMap<String, Object> comment) {
		userMapper.comment(comment);
	}
	
	public List<CommentVO> comment_view(int board_no1) {
		userMapper.comment_view(board_no1);
		List<CommentVO> c = userMapper.comment_view(board_no1);
		
		return c;
	}
}