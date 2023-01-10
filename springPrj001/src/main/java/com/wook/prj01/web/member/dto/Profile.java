package com.wook.prj01.web.member.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Profile {
	private int profile_no;
	private int user_no;
	private String nickname;
	private String imagename;
	private String introduction;
	private Date join_date;
	private Date profileUpdateDate;
	
}
