package com.wook.prj01.web.member.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wook.prj01.web.member.dto.Member;
import com.wook.prj01.web.member.service.MemberService;
import com.wook.prj01.web.token2.JwtTokenProvider;
import com.wook.prj01.web.token2.Token;

@CrossOrigin(origins = "*")
@RestController("memberController")
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberService service;

	@Autowired
	JwtTokenProvider tokenProvider; // new주면 안됨(@Value안됨)
	
	// https://velog.io/@shinhyocheol/%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B8%B0%EB%8A%A51
	// https://hojak99.tistory.com/430
	// (redis : 키-값 인메모리 DB 캐시같은 느낌(메모리에서 데이터 처리해서 빠름)
	// 캐시데이터, 토큰저장 -> 이래서 있는구거
	@RequestMapping("all")
	public int all(HttpServletResponse response) {
	
		// https://dev-yujji.tistory.com/63
		System.out.println(" all : ");
		String id = "id1";
		String pw = "1234";
		
		Member member = new Member();
		member.setUser_id(id);
		member.setPassword(pw);
		
		Token accessToken = tokenProvider.createAccessToken(member);
		Token refreshToken = tokenProvider.createRefreshToken(member);
		
		tokenProvider.setHeaderAccessToken(response, accessToken.getValue());
		// response에 Authorization 이거 왜 붙임?
		
//		redis서버가 먼데 = 캐시 서버(Remote Dictionary Server), 인메모리 데이터베이스 
		
		System.out.println("response : " + response);
		System.out.println("response" + response.getHeaderNames());
		
		
		System.out.println("accessToken : " + accessToken);
		System.out.println("refreshToken : " + refreshToken);
		
		
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
