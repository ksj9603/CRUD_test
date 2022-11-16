package com.example.vo;

public class CommentVO {

	private int board_no;
	private String text;
	private String comment_id;
	private String comment_time;
	
	public CommentVO(int board_no, String text, String comment_id, String comment_time) {
		this.board_no = board_no;
		this.text = text;
		this.comment_id = comment_id;
		this.comment_time = comment_time;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return comment_id;
	}

	public void setId(String comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
	
	
}
