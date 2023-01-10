package com.wook.prj01.web.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginTest {
	
	@RequestMapping("/loginTest1")
	public String loginTest() {
		System.out.println("loginTest-1 : " );
		return "loginTest";
	}
	
	@RequestMapping("/loginOk")
	public String loginOk() {
		System.out.println("Ok-1 : " );
		return "loginTest";
	}
	
	@RequestMapping("/loginNo")
	public String loginNo() {
		System.out.println("No-1 : " );
		return "loginTest";
	}
}
