package com.wook.prj01.web.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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

	@Autowired
	TokenProvider tokenProvider; // new주면 안됨(@Value가 안됨)

	@Autowired
	RedisController redisController;

	@RequestMapping("member")
	public int member(@RequestBody Map<String, Object> map) {
		System.out.println(map);
		int test = service.login();
		System.out.println("로그인테스트 member : " + test);
		return 1;
	}
	
	// 중

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

	
	// 토큰중
	
//	@Autowired
//	RedisRepository redisRepository;
	
	
	// https://velog.io/@shinhyocheol/%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B8%B0%EB%8A%A51
	// https://hojak99.tistory.com/430
	// https://velog.io/@hyehyeonmoon/%EB%8F%99%EA%B8%80-JWT%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8%EC%97%90%EC%84%9C-%EB%B3%B4%EC%95%88%EC%9D%84-%EB%86%92%EC%9D%B4%EB%8A%94-%EB%B0%A9%EB%B2%95HTTPS-Cookie

	// token 저장됐는지 확인
	@RequestMapping("tokenTest1")
	public ResponseEntity<String> all2(HttpServletResponse response) {
		Token refreshToken = null;
		ResponseEntity<String> result = redisController.getToken(refreshToken);
		System.out.println("all2 : " + result);
		return result;
	}

	@RequestMapping("tokenTest2")
	public ResponseEntity<?> all(HttpServletResponse response, CorsRegistry registry) {
		// https://dev-yujji.tistory.com/63
		String id = "id1";
		String pw = "1234";

		Member member = new Member();
		member.setUser_id(id);
		member.setPassword(pw);

		// redis에는 refreshToken 저장
//			redisRepository.save(refreshToken);

//			사용자 확인 후 토큰 발급
		Token accessToken = tokenProvider.createAccessToken(member);
		Token refreshToken = tokenProvider.createRefreshToken(member);

		// redis에 token
		redisController.setToken(refreshToken);

		tokenProvider.setHeaderAccessToken(response, accessToken.getValue());
		response.setHeader("Set-Cookie", refreshToken.toString());
		response.setHeader("Set-Cookie", accessToken.toString());

		ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken.getValue())
				.maxAge(7 * 24 * 60 * 60).path("/")
//		                .secure(true)
//		                .sameSite("None")
				.httpOnly(true) // https://github.com/axios/axios/issues/295
				.build();

		ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken.getValue())
				.maxAge(7 * 24 * 60 * 60).path("/")
//		                .secure(true) // 쿠키 안되면 확인해야할 것(1)
//		                .sameSite("None") // (2)
				.httpOnly(true).build();

		ResponseEntity<?> result;
		result = ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, accessCookie.toString())
				.header(HttpHeaders.SET_COOKIE, refreshCookie.toString()).build();

		registry.addMapping("/**") // 프로그램에서 제공하는 URL
				.allowedOrigins("*") // 청을 허용할 출처를 명시, 전체 허용 (가능하다면 목록을 작성한다.
				.allowedHeaders("*") // 어떤 헤더들을 허용할 것인지
				.allowedMethods("*") // 어떤 메서드를 허용할 것인지 (GET, POST...)
				.allowCredentials(false); // 쿠키 요청을 허용한다(다른 도메인 서버에 인증하는 경우에만 사용해야하며, true 설정시 보안상 이슈가 발생할 수 있다)
		// .maxAge(1500) // preflight 요청에 대한 응답을 브라우저에서 캐싱하는 시간 ;

//		        Cookie cookieTest = new Cookie("test1", "test1");
//		        response.addCookie(cookieTest);
		return result;
	}

}
