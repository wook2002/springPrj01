package com.wook.prj01.web.member.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController("memberController")
@RequestMapping("/member/")
public class MemberController {
	
//	@Autowired
//	private MemberService service;
	
	@RequestMapping("login")
	public void login() {
		System.out.println("로그인테스트");
	}

}
