package com.example.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class SearchVO {
	private int page;
	private int recordSize;
	private int pageSize;
	
	public SearchVO() {
		this.page = 1;
		this.recordSize = 5;
		this.pageSize = 10;
	}
	
	public int getOffset() {
		return (page -1) * recordSize;
	}
}
