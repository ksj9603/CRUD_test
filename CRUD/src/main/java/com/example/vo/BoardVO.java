package com.example.vo;

public class BoardVO {
	
	private int board_no;
	private String title;
	private String text;
	private String account_id;
	private String imagefile;
	private int likeboard;
	private int hateboard;
	
	public BoardVO() {
		
	}
	
	public BoardVO(String title, String text, String account_id, String imagefile) {
		this.title= title;
		this.text = text;
		this.account_id = account_id;
		this.imagefile = imagefile;
	}
	
	public BoardVO(String title, String text, String account_id, String imagefile, int likeboard, int hateboard) {
		this.title= title;
		this.text = text;
		this.account_id = account_id;
		this.imagefile = imagefile;
		this.likeboard = likeboard;
		this.hateboard = hateboard;
	}
	
	public BoardVO(int board_no, String account_id) {
		this.board_no = board_no;
		this.account_id = account_id;
	}
	
	public BoardVO(int board_no) {
		this.board_no = board_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getImagefile() {
		return imagefile;
	}

	public void setImagefile(String imagefile) {
		this.imagefile = imagefile;
	}

	public int getLikeboard() {
		return likeboard;
	}

	public void setLikeboard(int likeboard) {
		this.likeboard = likeboard;
	}

	public int getHateboard() {
		return hateboard;
	}

	public void setHateboard(int hateboard) {
		this.hateboard = hateboard;
	}
	
	

}