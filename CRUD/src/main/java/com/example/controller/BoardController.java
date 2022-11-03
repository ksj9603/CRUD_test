package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.service.BoardService;
import com.example.vo.BoardVO;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardPage")
	public String boardPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		model.addAttribute("id",session.getAttribute("loginSession"));
		return "board";
	}
	
	@PostMapping("/boardUpload")
	public String boardUpload(Model model, BoardVO boardVO) {
		
		boardService.BoardUpload(boardVO);
		
		List<BoardVO> boardList = boardService.getBoardList();
		
		model.addAttribute("board",boardList);
		return "main";
	}
}
