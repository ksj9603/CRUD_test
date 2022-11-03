package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	
	private int board_no;
	private String title;
	private String text;
	private String account_id;
	
	public BoardVO(String title, String text, String account_id) {
		this.title= title;
		this.text = text;
		this.account_id = account_id;
	}

}
