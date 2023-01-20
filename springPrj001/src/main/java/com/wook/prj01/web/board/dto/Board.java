package com.wook.prj01.web.board.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class Board {
	private int no;
	private String title;
	private String content;
	private String regDate;
	private int recommend;
	private int lookup;
	private String category;
	private String userId;
	private int RN;
	private int categoryNo;
	
	
	
	public Board() {
//		Date date = new Date();
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = simpleDateFormat.format(date);
//        java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);
//
//        System.out.println("Util Date: " + date);
//        System.out.println("formattedDate: " + formattedDate);
//        System.out.println("SQL Date: " + date1);
	}
	
	
}


