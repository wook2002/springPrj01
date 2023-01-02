package com.wook.prj01.web.board.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Board {
	private int no;
	private String title;
	private String content;
	private Date regDate;
	private int recommend;
	private int lookup;
	private String category;
	private String userId;
	private int RN;
	private int categoryNo;
}
