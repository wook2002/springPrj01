package com.wook.prj01.web.board.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Board {
	private int POST_NO;
	private String POST_TITLE;
	private String POST_CONTENT;
	private Date REG_DATE;
	private int RECOMMEND;
	private int LOOKUP;
	private String category_name;
	private String user_id;
	
	
	
}
