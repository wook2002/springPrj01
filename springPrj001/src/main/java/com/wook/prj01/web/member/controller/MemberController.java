package com.wook.prj01.web.member.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.wook.prj01.web.member.dto.Member;
import com.wook.prj01.web.member.service.MemberService;
import com.wook.prj01.web.redis.RedisController;
import com.wook.prj01.web.token2.Token;
import com.wook.prj01.web.token2.TokenProvider;

//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "*")
@RestController("memberController")
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
//	@Autowired
//	RedisRepository redisRepository;

	@Autowired
	TokenProvider tokenProvider; // new주면 안됨(@Value안됨)
	
	@Autowired
	RedisController redisController;
	
	// https://velog.io/@shinhyocheol/%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B8%B0%EB%8A%A51
	// https://hojak99.tistory.com/430
	// https://velog.io/@hyehyeonmoon/%EB%8F%99%EA%B8%80-JWT%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8%EC%97%90%EC%84%9C-%EB%B3%B4%EC%95%88%EC%9D%84-%EB%86%92%EC%9D%B4%EB%8A%94-%EB%B0%A9%EB%B2%95HTTPS-Cookie
	// (redis : 키-값 인메모리 DB 캐시같은 느낌(메모리에서 데이터 처리해서 빠름)
	// 캐시데이터, 토큰저장 -> 이래서 있는구거
	
	// token 저장됐는지 확인
	@RequestMapping("all2")
	public ResponseEntity<String> all2(HttpServletResponse response, CorsRegistry registry) {
		 registry.addMapping("/**").allowedMethods("*").allowedOriginPatterns("*").allowCredentials(true);
		 
		System.out.println(" all2 : ");
		// imple에서 id 담음(임시)
		Token refreshToken = null;
		ResponseEntity<String> result = redisController.getToken(refreshToken);
		System.out.println("all2 : "	+ result);
		return result;
	}
	
	@RequestMapping("all1")
	public ResponseEntity<?> all(HttpServletResponse response) {
		// https://dev-yujji.tistory.com/63
		System.out.println(" all1 : ");
		String id = "id1";
		String pw = "1234";
		
		Member member = new Member();
		member.setUser_id(id);
		member.setPassword(pw);
		
		// redis에는 refreshToken 저장
//		redisRepository.save(refreshToken);
		
//		사용자 확인 후 토큰 발급
		Token accessToken = tokenProvider.createAccessToken(member);
		Token refreshToken = tokenProvider.createRefreshToken(member);

		System.out.println("refreshToken : " + refreshToken);
		System.out.println("accessToken : " + accessToken);
		
		// redis에 token
		redisController.setToken(refreshToken);
		
		//
		tokenProvider.setHeaderAccessToken(response, accessToken.getValue());
		System.out.println("-------------------------");
		System.out.println("response : " + response);
		System.out.println("response" + response.getHeaderNames());
		System.out.println("accessToken : " + accessToken);
		System.out.println("refreshToken : " + refreshToken);
		System.out.println("refreshToken.toString() : " + refreshToken.toString());
		
		response.setHeader("Set-Cookie", refreshToken.toString());
		response.setHeader("Set-Cookie", accessToken.toString());
		
//		https://indepth.dev/posts/1382/localstorage-vs-cookies
//		https://velog.io/@myway00/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%EB%A6%AC%EC%95%A1%ED%8A%B8-jwt-%ED%86%A0%ED%81%B0-%EC%84%A4%EC%A0%95%EB%B0%A9%EB%B2%95
		 ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken.getValue())
	                .maxAge(7 * 24 * 60 * 60)
	                .path("/")
//	                .secure(true)
//	                .sameSite("None")
//	                .httpOnly(true)  //https://github.com/axios/axios/issues/295
	                .build();
		 
		 ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken.getValue())
	                .maxAge(7 * 24 * 60 * 60)
	                .path("/")
	                .secure(true) // 쿠키 안되면 확인해야할 것(1)
	                .sameSite("None") // (2)
	                .httpOnly(true)  
	                .build();
		 
		 ResponseEntity<?> result;
		 
//		 result = ResponseEntity.ok()
//					.header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
//					.build();
		 
		 result = ResponseEntity.ok()
		 			.header(HttpHeaders.SET_COOKIE, accessCookie.toString())
		 			.build();
		 			
		 Cookie cookieTest = new Cookie("name", "value");
        response.addCookie(cookieTest);
	        
	        
	        System.out.println("result : " + result);
	        
		return result;
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
