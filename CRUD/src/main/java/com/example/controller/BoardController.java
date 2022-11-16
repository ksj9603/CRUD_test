package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.BoardService;
import com.example.vo.BoardVO;
import com.example.vo.CommentVO;
import com.example.vo.SearchVO;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class BoardController {
	int cate = 0;
	@Autowired
	private BoardService boardService;
	
	private final String dir = "C:\\Dev\\image\\";
	
	@GetMapping("/board/boardPage")
	public String boardPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginSession") == null) {
			return "login";
		}
		model.addAttribute("id",session.getAttribute("loginSession"));
		return "board";
	}
	
	@PostMapping("/board/boardUpload")
	public String boardUpload(Model model, @RequestParam("imageFiles") MultipartFile imageFiles, BoardVO boardVO, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		System.out.println(imageFiles.getOriginalFilename());
		if(!imageFiles.isEmpty()) {
			String imagefile = imageFiles.getOriginalFilename();
			boardVO.setImagefile(imagefile);
			String fullPath = dir + imagefile;
			imageFiles.transferTo(new File(fullPath));
		} else {
			boardVO.setImagefile(null);
		}
		
		boardService.BoardUpload(boardVO);
		
		List<BoardVO> boardList = boardService.getBoardList(cate);
		
		model.addAttribute("board",boardList);
		model.addAttribute("id", session.getAttribute("loginSession"));
		return "main";
	}
	@PostMapping("/board/boardAlter") 
	public String boardAlter(Model model, BoardVO boardVO,@RequestParam("imageFiles") MultipartFile imageFiles, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		if(!imageFiles.isEmpty()) {
			String imagefile = imageFiles.getOriginalFilename();
			boardVO.setImagefile(imagefile);
			String fullPath = dir + imagefile;
			imageFiles.transferTo(new File(fullPath));
		}
		boardService.boardAlter(boardVO);
		System.out.println(boardVO.getAccount_id());
		
		List<BoardVO> boardList = boardService.getBoardList(cate);
		
		model.addAttribute("board",boardList);
		model.addAttribute("id", session.getAttribute("loginSession"));
		
		
		return "main";
	}
	@GetMapping("/board/boardList")
	public String boardList() {
		return "/main";
	}
	
	@PostMapping("/board/getBoardList")
	@ResponseBody
	public List<BoardVO> boardList(@RequestParam("category") int category, Model model, BoardVO boardVO, SearchVO searchVO ,HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("id",session.getAttribute("loginSession"));
		HashMap<String, Object> board = new HashMap<String, Object>();
		
		System.out.println(category);
		
		board.put("category", category);
		board.put("searchVO", searchVO);
		List<BoardVO> boardList = boardService.findAll(board);
//		List<BoardVO> boardList = boardService.getBoardList();
		
		model.addAttribute("board",boardList);
		System.out.println(boardList);
		return boardList;
	}
	@PostMapping("/board/changePage")
	@ResponseBody
	public List<BoardVO> changePage(@RequestParam("category") int category,@RequestParam("page") int page, Model model, BoardVO boardVO, SearchVO searchVO ,HttpServletRequest request) {
		searchVO.setPage(page);
		HttpSession session = request.getSession();
		model.addAttribute("id",session.getAttribute("loginSession"));
		
		HashMap<String, Object> board = new HashMap<String, Object>();
		
		board.put("category", category);
		board.put("searchVO", searchVO);
		List<BoardVO> boardList = boardService.findAll(board);
//		List<BoardVO> boardList = boardService.getBoardList();
		
		model.addAttribute("board",boardList);
		System.out.println(boardList);
		return boardList;
	}
	
	@PostMapping("/board/getAllBoardList")
	@ResponseBody
	public List<BoardVO> boardList1(@RequestParam("category") int category, Model model, BoardVO boardVO, SearchVO searchVO ,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		model.addAttribute("id",session.getAttribute("loginSession"));
//		List<BoardVO> boardList = boardService.findAll(searchVO);
		
		
		List<BoardVO> boardList1 = boardService.getBoardList(category);
		
		model.addAttribute("board1",boardList1);
		System.out.println(boardList1);
		return boardList1;
	}
	
	@GetMapping(value="/board/deleteBoard/*")
	public String delUserList(@RequestParam int board_no, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String account_id = (String) session.getAttribute("loginSession");
		
		
		boardService.delBoard(board_no, account_id);
		
		List<BoardVO> boardList = boardService.getBoardList(cate);
		model.addAttribute("board",boardList);
		
		return "/main";
	}
	
	@GetMapping(value="/board/alterBoard/*")
	public String alterBoard(@RequestParam int board_no, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("loginSession");
		List<BoardVO> board = boardService.alterBoard(board_no);
		if (board.get(0).getAccount_id().equals(a)){
			model.addAttribute("board",board);
			return "alterBoard";
		}
		return "redirect:/board/boardList";
		
	}
	
	@PostMapping("/board/searchBoard")
	@ResponseBody
	public List<BoardVO> searchBoard(@RequestParam("category") int category,@RequestParam("search") String search, @RequestParam("droper") String droper, SearchVO searchVO ,Model mode ,HttpServletRequest request) {
//		System.out.println(searchVO.getRecordSize());
//		System.out.println(search);
//		System.out.println(drop);
		System.out.println("search"+ search);
		HashMap<String, Object> board = new HashMap<String, Object>();
		board.put("search", search);
		board.put("searchVO", searchVO);
		board.put("droper", droper);
		board.put("category", category);
		List<BoardVO> boardList = boardService.searchBoard(board);
		
		return boardList;
	}
	@PostMapping("/board/searchBoardpage")
	@ResponseBody
	public List<BoardVO> searchBoardpage(@RequestParam("category") int category,@RequestParam("search") String search, @RequestParam("page") int page,@RequestParam("droper") String droper, SearchVO searchVO ,Model mode ,HttpServletRequest request) {
//		System.out.println(searchVO.getRecordSize());
//		System.out.println(search);
		searchVO.setPage(page);
		HashMap<String, Object> board = new HashMap<String, Object>();
		board.put("search", search);
		board.put("searchVO", searchVO);
		board.put("droper", droper);
		board.put("category", category);
		List<BoardVO> boardList = boardService.searchBoard(board);
		
		return boardList;
	}
	
	@PostMapping("/board/searchAllBoard")
	@ResponseBody
	public List<BoardVO> searchAllBoard(@RequestParam("category") int category,@RequestParam("search") String search,@RequestParam("droper") String droper, Model model, HttpServletRequest request) {
		HashMap<String, Object> board = new HashMap<String, Object>();
		board.put("search", search);
		board.put("droper", droper);
		board.put("category", category);
		List<BoardVO> boardList = boardService.searchAllBoard(board);
		return boardList;
	}
	
	@GetMapping("/board/boardInfo")
	public String boardInfo(@RequestParam int board_no, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		model.addAttribute("board_no",board_no);
		model.addAttribute("loginSession",session.getAttribute("loginSession"));
		
		System.out.println("board_no :: "+board_no);
		return "/boardInfo";
	}
	
	@PostMapping("/board/boardInfoData")
	@ResponseBody
	public HashMap<String, Object> boardInfoData(@RequestParam("board_no1") int board_no1) {
		List<BoardVO> board = boardService.boardInfoData(board_no1);
		List<CommentVO> comm = boardService.comment_view(board_no1);
		
		HashMap<String, Object> go = new HashMap<String, Object>();
		go.put("board", board);
		go.put("comm", comm);
		return go;
	}
	
	@PostMapping("/board/boardLikeHate")
	@ResponseBody
	public List<BoardVO> boardLikeHate(@RequestParam("bt") int bt, @RequestParam("board_no1") int board_no1, @RequestParam("account_id") String account_id) {
		
		int count = boardService.thumbCheck(account_id);
		
		if(count >= 3 ) {
			boolean thumbTime = boardService.thumbTime(account_id);
			if(thumbTime == true) {
				boardService.thumbTimeChange(account_id);
				HashMap<String, Object> hash = new HashMap<String, Object>();
				hash.put("bt", bt);
				hash.put("board_no1", board_no1);
				hash.put("account_id", account_id);
				boardService.boardLikeHate(hash);
				boardService.thumbCnt(account_id);
				
				List<BoardVO> board = boardService.boardInfoData(board_no1);
				
				return board;
			}
			return null;
		}
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("bt", bt);
		hash.put("board_no1", board_no1);
		hash.put("account_id", account_id);
		boardService.boardLikeHate(hash);
		boardService.thumbCnt(account_id);
		
		List<BoardVO> board = boardService.boardInfoData(board_no1);
		
		return board;
	}
	
	@PostMapping("/board/comment")
	@ResponseBody
	public List<CommentVO> comment(@RequestParam("board_no1") int board_no1, @RequestParam("account_id") String account_id, @RequestParam("comment_val") String comment_val) {
		
		HashMap<String,Object> comment = new HashMap<String,Object>();
		comment.put("board_no1", board_no1);
		comment.put("comment_val", comment_val);
		comment.put("account_id", account_id);
		
		boardService.comment(comment);
		
		List<CommentVO> comm = boardService.comment_view(board_no1);
		
		return comm;
		
	}
}