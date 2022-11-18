package com.example.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	int cate = 0;
	
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
		// 0: 정보 틀림, 1: 1분Lock, 2: 로그인OK
		
		String checkNull = loginService.checkNullId(userVO);
		System.out.println(checkNull);
		if(checkNull == null) {
			return 0;
		}
		
		boolean lock = loginService.accountLock(userVO);
		// 계정에 Lock 여부 확인
		if(lock == false) {
			boolean check = loginService.checkLogin(userVO); // 로그인 정보 일치 확인
			if(check == false) {
				int cnt = loginService.loginCnt(userVO);
				cnt += 1;
				userVO.setLogin_cnt(cnt);
				loginService.cntChange(userVO); // 로그인시도 횟수 증가 
			
				int id_cnt = loginService.checkCntId(userVO);
				
				if (id_cnt < 3) { 
					loginService.lastLogin(userVO);
					return 0;
				}else {
					loginService.lastLogin(userVO);
					loginService.changeLock(userVO);
					return 1;						
					}
				}else {
					loginService.lastLogin(userVO);
					
					userVO.setLogin_cnt(0);
					loginService.cntChange(userVO);
					HttpSession session = request.getSession();
					session.setAttribute("loginSession", userVO.getId());
					return 2;
				}
			} 
			else {
				boolean lastLoginCheck = loginService.lastLoginCheck(userVO);
				if(lastLoginCheck == false) {
					return 1;
				} else {
					boolean lockAfterCheck = loginService.checkLogin(userVO);
					if(lockAfterCheck == false) {
						loginService.lastLogin(userVO);
						return 1;
					}
					loginService.lastLogin(userVO);

					userVO.setLogin_cnt(0);
					loginService.cntChange(userVO);
					HttpSession session = request.getSession();
					session.setAttribute("loginSession", userVO.getId());
						loginService.changeLock(userVO);
						return 2;
			}
		}
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