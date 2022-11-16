package com.example.vo;

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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
