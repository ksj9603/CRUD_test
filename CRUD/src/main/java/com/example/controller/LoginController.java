package com.example.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.BoardService;
import com.example.service.LoginService;
import com.example.vo.BoardVO;
import com.example.vo.SearchVO;
import com.example.vo.UserVO;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private BoardService boardService;
	
	int cate = 0;
	int cnt = 0;
	
	@GetMapping("/")
	public String loginPage(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginSession");
		System.out.println(id);
		if(id != null) {
			List<BoardVO> boardList = boardService.getBoardList(cate);
			model.addAttribute("board",boardList);
			
			model.addAttribute("id",session.getAttribute("loginSession"));
			return "/main";
		}
		return "/login";
	}
	
	@PostMapping(value="/accountCheck")
	@ResponseBody
	public int gogo(UserVO userVO, HttpServletRequest request) throws InterruptedException {
		System.out.println(userVO.getId());
		boolean check = loginService.checkLogin(userVO);
		loginService.lastLogin(userVO);
		if(check == false) {
			cnt += 1;
			userVO.setLogin_cnt(cnt);
			loginService.cntChange(userVO);
			
			int id_cnt = loginService.checkCntId(userVO);
			if (id_cnt >= 3) {
				
				return 1;
				
				
			}
			
			return 0;
		} 
		
		cnt = 0;
		userVO.setLogin_cnt(cnt);
		loginService.cntChange(userVO);
		HttpSession session = request.getSession();
		session.setAttribute("loginSession", userVO.getId());
		
		return 2;
	}	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginSession");
		session.removeAttribute(id);
		session.invalidate();
		return "/login";
	}
	
}