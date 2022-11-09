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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.BoardService;
import com.example.vo.BoardVO;
import com.example.vo.FileVO;
import com.example.vo.SearchVO;
import com.example.vo.UserVO;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	private final String dir = "C:\\Dev\\image\\";
	
	@GetMapping("/main2")
	public String main2() {
		return "main2";
	}
	
	@GetMapping("/boardPage")
	public String boardPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginSession") == null) {
			return "Login";
		}
		model.addAttribute("id",session.getAttribute("loginSession"));
		return "board";
	}
	
	@PostMapping("/boardUpload")
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
		
		List<BoardVO> boardList = boardService.getBoardList();
		
		model.addAttribute("board",boardList);
		model.addAttribute("id", session.getAttribute("loginSession"));
		return "main";
	}
	@PostMapping("/boardAlter") 
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
		
		List<BoardVO> boardList = boardService.getBoardList();
		
		model.addAttribute("board",boardList);
		model.addAttribute("id", session.getAttribute("loginSession"));
		
		
		return "main";
	}
	@GetMapping("/boardList")
	public String boardList() {
		return "/main";
	}
	
	@PostMapping("/getBoardList")
	@ResponseBody
	public List<BoardVO> boardList(Model model, BoardVO boardVO, SearchVO searchVO ,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		model.addAttribute("id",session.getAttribute("loginSession"));
		List<BoardVO> boardList = boardService.findAll(searchVO);
//		List<BoardVO> boardList = boardService.getBoardList();
		
		model.addAttribute("board",boardList);
		System.out.println(boardList);
		return boardList;
	}
	@PostMapping("/changePage")
	@ResponseBody
	public List<BoardVO> changePage(@RequestParam("page") int page, Model model, BoardVO boardVO, SearchVO searchVO ,HttpServletRequest request) {
		System.out.println(page);
		searchVO.setPage(page);
		HttpSession session = request.getSession();
		model.addAttribute("id",session.getAttribute("loginSession"));
		List<BoardVO> boardList = boardService.findAll(searchVO);
//		List<BoardVO> boardList = boardService.getBoardList();
		
		model.addAttribute("board",boardList);
		System.out.println(boardList);
		return boardList;
	}
	
	@PostMapping("/getAllBoardList")
	@ResponseBody
	public List<BoardVO> boardList1(Model model, BoardVO boardVO, SearchVO searchVO ,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		model.addAttribute("id",session.getAttribute("loginSession"));
//		List<BoardVO> boardList = boardService.findAll(searchVO);
		List<BoardVO> boardList1 = boardService.getBoardList();
		
		model.addAttribute("board1",boardList1);
		System.out.println(boardList1);
		return boardList1;
	}
	
	@GetMapping(value="/deleteBoard/*")
	public String delUserList(@RequestParam int board_no, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String account_id = (String) session.getAttribute("loginSession");
		
		
		boardService.delBoard(board_no, account_id);
		
		List<BoardVO> boardList = boardService.getBoardList();
		model.addAttribute("board",boardList);
		
		return "/main";
	}
	
	@GetMapping(value="/alterBoard/*")
	public String alterBoard(@RequestParam int board_no, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("loginSession");
		List<BoardVO> board = boardService.alterBoard(board_no);
		if (board.get(0).getAccount_id().equals(a)){
			model.addAttribute("board",board);
			return "alterBoard";
		}
		return "redirect:/boardList";
		
	}
	
	@PostMapping("/searchBoard")
	@ResponseBody
	public List<BoardVO> searchBoard(@RequestParam("search") String search, @RequestParam("droper") String droper, SearchVO searchVO ,Model mode ,HttpServletRequest request) {
//		System.out.println(searchVO.getRecordSize());
//		System.out.println(search);
//		System.out.println(drop);
//		System.out.println(search);
		HashMap<String, Object> board = new HashMap<String, Object>();
		board.put("search", search);
		board.put("searchVO", searchVO);
		board.put("droper", droper);
		List<BoardVO> boardList = boardService.searchBoard(board);
		
		return boardList;
	}
	@PostMapping("/searchBoardpage")
	@ResponseBody
	public List<BoardVO> searchBoardpage(@RequestParam("search") String search, @RequestParam("page") int page,@RequestParam("droper") String droper, SearchVO searchVO ,Model mode ,HttpServletRequest request) {
//		System.out.println(searchVO.getRecordSize());
//		System.out.println(search);
		searchVO.setPage(page);
		HashMap<String, Object> board = new HashMap<String, Object>();
		board.put("search", search);
		board.put("searchVO", searchVO);
		board.put("droper", droper);
		List<BoardVO> boardList = boardService.searchBoard(board);
		
		return boardList;
	}
	
	@PostMapping("/searchAllBoard")
	@ResponseBody
	public List<BoardVO> searchAllBoard(@RequestParam("search") String search,@RequestParam("droper") String droper, Model model, HttpServletRequest request) {
		HashMap<String, Object> board = new HashMap<String, Object>();
		board.put("search", search);
		board.put("droper", droper);
		List<BoardVO> boardList = boardService.searchAllBoard(board);
		return boardList;
	}
}