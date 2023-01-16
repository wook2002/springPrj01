package com.wook.prj01.web.token2;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.wook.prj01.web.member.dto.Member;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

// https://seeminglyjs.tistory.com/369
// https://blog.naver.com/PostView.nhn?blogId=varkiry05&logNo=222296954508&categoryNo=107&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView
// https://cloud.google.com/iot/docs/how-tos/credentials/jwts
// https://dev-yujji.tistory.com/63

// @RequiredArgsConstructor : https://medium.com/webeveloper/requiredargsconstructor-%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9D%98%EC%A1%B4%EC%84%B1-%EC%A3%BC%EC%9E%85-dependency-injection-4f1b0ac33561


// https://velog.io/@solchan/%EB%B0%B1%EC%97%85-Redis%EB%A5%BC-%ED%86%B5%ED%95%9C-JWT-Refresh-Token-%EA%B4%80%EB%A6%AC
// https://gksdudrb922.tistory.com/217
// https://blog.naver.com/PostView.naver?blogId=jinwoo6612&logNo=222462211251&parentCategoryNo=&categoryNo=32&viewDate=&isShowPopularPosts=true&from=search

@Component
public class TokenProvider {
	
	@Value("${jwt.security.key}")
    private String secretKey;
    @Value("${jwt.response.header}")
    private String jwtHeader;
    @Value("${jwt.token.prefix}")
    private String jwtTokenPrefix;
    
	// 비밀키(HS256)
	private SecretKey SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	// 만료시간
	private long accessValidTime = 5 * 60 * 1000; // 5분
	private long refreshValidTime = 10 * 60 * 1000; // 10분
	
	// jwt-header(암호화방법)
	private String typ = "JWT";
	private String alg = "HS256";
	
	// jwt-payloads(데이터)
	private String data1 = "data1";
	private String data2 = "data2";
	
	
//	@Autowired
//    private RedisRepository redisRepository;
	
	 // AccessToken 생성
	 public Token createAccessToken(Member member) {
	        return createToken(member, accessValidTime, "access-token");
	    }
	 // RefreshToken 생성
	 public Token createRefreshToken(Member member) {
	        return createToken(member, refreshValidTime, "refresh-token");
	    }
	
	 // response-Headers에 Authorization와 accessToken토큰이 들어감
	 public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
		 System.out.println("jwtHeader : " + jwtHeader); // Authorization
		 System.out.println("secretKey : " + secretKey); // JWT
		 System.out.println("jwtTokenPrefix : " + jwtTokenPrefix); // Bearer
		 System.out.println("accessToken2 : " + accessToken); //
		 
        response.setHeader(jwtHeader, accessToken); //이거왜붙임?(Authorization)
    }
	 
	 // JWT 토큰에서 인증 정보 조회
	    public void getAuthentication(String token) {
	    }
	 
	 
	 
	public Token createToken(
		Member member, long validTime, String setSubject) {
		
		 Long now = System.currentTimeMillis();
		 Map<String, Object> headers = headers(typ, alg);
		 Map<String, Object> payloads = payloads(data1, data2);
		 
		 
		 
		String jwtToken = Jwts.builder()
	    		.setSubject(setSubject)
				.setHeader(headers)
				.setClaims(payloads) 
				.signWith(SignatureAlgorithm.HS256, SecretKey) 
				.setExpiration(new Date(now + validTime))
				.compact(); 
		System.out.println("jwtToken : " + jwtToken);
		
		return Token.builder()
			.key(member.getUser_id())
			.value(jwtToken)
			.expiredTime(validTime)
			.build();
	}
	
	public Map<String, Object> headers(String typ, String alg){
		Map<String, Object> headers = new HashMap<>();
	    headers.put("typ", typ); 
	    headers.put("alg", alg);
	    return headers;
	}
	
	public Map<String, Object> payloads(String data1, String data2){
		Map<String, Object> payloads = new HashMap<>();
		payloads.put("data1", data1); 
		payloads.put("data2", data2);
	    return payloads;
	}
	
     
	// 토큰생성
//	public String createJwt(String param1, String param2) {
//        // // https://inpa.tistory.com/entry/WEB-%F0%9F%93%9A-Access-Token-Refresh-Token-%EC%9B%90%EB%A6%AC-feat-JWT
//        // 토큰생성 Builder
//        Long now = System.currentTimeMillis();
//        String accessToken = Jwts.builder()
//        		.setSubject("access-token")
//                .setHeader(headers) // Headers 설정
//                .setClaims(payloads) // Claims(데이터 단위) 설정
//                .signWith(SignatureAlgorithm.HS256, SecretKey) // HS256과 비밀Key로 Sign(서명)
//                .setExpiration(new Date(now + accessValidTime))
//                .compact(); // 토큰 생성
//        String refreshToken = Jwts.builder()
//        		.setSubject("refresh-token")
//                .setHeader(headers)
//                .setClaims(payloads) 
//                .signWith(SignatureAlgorithm.HS256, SecretKey) 
//                .setExpiration(new Date(now + refreshValidTime))
//                .compact();
//		return null;
//	}
        
     
	


    


}
