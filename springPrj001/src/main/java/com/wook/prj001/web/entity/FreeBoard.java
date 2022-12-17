package com.wook.prj001.web.entity;

import java.util.Date;

public class FreeBoard {
	private int no;
	private String title;
	private String content;
	private String writer;
	private Date freeDate;
	private int recommend;
	private int lookup;

	public FreeBoard() {
		// TODO Auto-generated constructor stub
	}

	public FreeBoard(int no, String title, String content, String writer, Date freeDate, int recommend, int lookup) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.freeDate = freeDate;
		this.recommend = recommend;
		this.lookup = lookup;
	}



	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getFreeDate() {
		return freeDate;
	}

	public void setFreeDate(Date freeDate) {
		this.freeDate = freeDate;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getLookup() {
		return lookup;
	}

	public void setLookup(int lookup) {
		this.lookup = lookup;
	}
	
	
	
}