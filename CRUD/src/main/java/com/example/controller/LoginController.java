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
import com.example.service.LoginService;
import com.example.vo.BoardVO;
import com.example.vo.UserVO;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/sendData")
	public String login(UserVO userVO, Model model, HttpServletRequest request) {
		boolean check = loginService.checkLogin(userVO);
		String error = "아이디 및 비밀번호가 틀립니다.";
		String id = userVO.getId();
		if(check == false) {
			model.addAttribute("error",error);
			return "/Login";
		}
		HttpSession session = request.getSession();
		session.setAttribute("loginSession", id);
		
		List<BoardVO> boardList = boardService.getBoardList();
		
		model.addAttribute("id",session.getAttribute("loginSession"));
		model.addAttribute("board",boardList);
		
		return "/main";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginSession");
		System.out.println(id);
		if(id != null) {
			List<BoardVO> boardList = boardService.getBoardList();
			model.addAttribute("board",boardList);
			
			model.addAttribute("id",session.getAttribute("loginSession"));
			return "/main";
		}
		return "/Login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginSession");
		session.removeAttribute(id);
		session.invalidate();
		return "/Login";
	}
	
//	@GetMapping("/")
//	public String sessionLogin(HttpServletRequest request, Model model) {
//		HttpSession session = request.getSession(false);
//		if(session == null) {
//			return "Login";
//		}
//		Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//	}
}