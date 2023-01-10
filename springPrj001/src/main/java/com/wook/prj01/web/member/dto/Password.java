package com.wook.prj01.web.member.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Password {
	private int password_no;
	private int user_no;
	private String salt;
	private String password;
	private Date pwUpdateDate;
}
