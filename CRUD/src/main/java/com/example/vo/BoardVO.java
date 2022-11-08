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
	private String imagefile;
	
	
	public BoardVO(String title, String text, String account_id, String imagefile) {
		this.title= title;
		this.text = text;
		this.account_id = account_id;
		this.imagefile = imagefile;
	}
	
	public BoardVO(int board_no, String account_id) {
		this.board_no = board_no;
		this.account_id = account_id;
	}
	
	public BoardVO(int board_no) {
		this.board_no = board_no;
	}

}