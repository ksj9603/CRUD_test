package com.example.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.vo.BoardVO;
import com.example.vo.SearchVO;
import com.example.vo.UserVO;

@Mapper
@Repository
public interface UserMapper {
	
	void save(UserVO userVO);
	
	void boardUpload(BoardVO boardVO);
	
	public List<UserVO> getList();
	
	void delUser(String id);
	
	void delBoard(@Param("board_no") int board_no, @Param("account_id")String account_id);
	
	public boolean checkId(UserVO userVO);
	
	public boolean checkBoard(String board_no, String account_id);
	
	public List<UserVO> alterUser(String id);
	
	public void changeUser(UserVO userVO);
	
	public boolean checkLogin(UserVO userVO);
	
	public List<BoardVO> getBoardList(int category);
	
	public List<BoardVO> alterBoard(int board_no);
	
	public void boardAlter(BoardVO boardVO);
	
	public List<BoardVO> findAll(HashMap<String, Object> board);
	
	public List<BoardVO> searchBoard(HashMap<String, Object> board);
	
	public List<BoardVO> searchAllBoard(HashMap<String, Object> board);
	
	public void cntChange(UserVO userVO);
	
	public int checkCntId(UserVO userVO);
	
	void lastLogin(UserVO userVO);
	
	public boolean lastLoginCheck(UserVO userVO);
	
	public boolean accountLock(UserVO userVO);
	
	void changeLock(UserVO userVO);
	
	public String checkNullId(UserVO userVO);
	
	public List<BoardVO> boardInfoData(int board_no1);
	
	public void boardLikeHate(HashMap<String, Object> hash);
}