package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class TestController {
	@GetMapping("/boardInfo")
	public String boardInfo() {
		return "/boardInfo";
	}
}
