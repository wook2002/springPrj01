package com.wook.prj01.web.member.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wook.prj01.web.member.service.MemberService;
import com.wook.prj01.web.token2.JwtTokenProvider;

@CrossOrigin(origins = "*")
@RestController("memberController")
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberService service;

	
	// https://velog.io/@shinhyocheol/%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B8%B0%EB%8A%A51
	@RequestMapping("all")
	public int all(HttpServletResponse response) {
	
		System.out.println(" all : ");
		String id = "id1";
		String pw = "1234";
		
		JwtTokenProvider jw = new JwtTokenProvider();
		String result = jw.createJwt(id, pw);
		System.out.println("result : " + result);
		
		return 1;
	}
	
	
	@RequestMapping("member")
	public int member() {
		
		int test = service.login();
		System.out.println("로그인테스트 member : " + test);
	
		return 1;
	}
	
	@RequestMapping("admin")
	public int admin() {
		int test = service.login();
		System.out.println("로그인테스트 admin : " + test);
		
		return 1;
	}
	
	@RequestMapping("errertest")
	public int errertest() {
		int test = service.login();
		System.out.println("errertest : " + test);
		return 1;
	}
	
	@RequestMapping("loginSucces")
	public int loginSucces() {
		int test = service.login();
		System.out.println("loginSucces : " + test);
		return 1;
	}
	
	@RequestMapping("loginFailure")
	public int loginFailure() {
		int test = service.login();
		System.out.println("loginFailure : " + test);
		return 1;
	}

}
