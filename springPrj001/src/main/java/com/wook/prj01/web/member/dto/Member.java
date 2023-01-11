package com.wook.prj01.web.member.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Member {
//	MEMBER_USER
	private int user_no;
	private String user_id;	
//	MEMBER_PASSWORD
	private int password_no;
	private String salt;
	private String password;
	private Date pwUpdateDate;
//	MEMBER_PROFILE
	private int profile_no;
	private String nickname;
	private String imagename;
	private String introduction;
	private Date join_date;
	private Date profileUpdateDate;
//	MEMBER_AUTHENTICATION
	private int authentication_no;
	private String cell_phone;
	private String email;
	private String birtyday;
	private int gender;
	private String addr1;
	private String addr2;
	private Date auth_date;
}
