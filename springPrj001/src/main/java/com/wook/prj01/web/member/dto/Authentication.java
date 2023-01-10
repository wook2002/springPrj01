package com.wook.prj01.web.member.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Authentication {
	private int authentication_no;
	private int user_no;
	private String cell_phone;
	private String email;
	private String birtyday;
	private int gender;
	private String addr1;
	private String addr2;
	private Date auth_date;
	
}
