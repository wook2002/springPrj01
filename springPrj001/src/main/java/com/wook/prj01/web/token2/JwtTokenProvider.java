package com.wook.prj01.web.token2;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

// https://seeminglyjs.tistory.com/369
// https://blog.naver.com/PostView.nhn?blogId=varkiry05&logNo=222296954508&categoryNo=107&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView
// https://cloud.google.com/iot/docs/how-tos/credentials/jwts

// @RequiredArgsConstructor : https://medium.com/webeveloper/requiredargsconstructor-%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9D%98%EC%A1%B4%EC%84%B1-%EC%A3%BC%EC%9E%85-dependency-injection-4f1b0ac33561
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
	
	// 비밀키(HS256)
	private SecretKey SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	// 만료시간
	private long accessValidTime = 5 * 60 * 1000; // 5분
	private long refreshValidTime = 10 * 60 * 1000; // 10분
	
	// 생성자(토큰생성)
	public String createJwt(String param1, String param2) {

		//Header(암호화방법)
		Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT"); 
        headers.put("alg", "HS256");
        
        //payload(데이터)
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("param1", param1);
        payloads.put("param2", param2);
        
        
        // // https://inpa.tistory.com/entry/WEB-%F0%9F%93%9A-Access-Token-Refresh-Token-%EC%9B%90%EB%A6%AC-feat-JWT
        // 토큰생성 Builder
        Long now = System.currentTimeMillis();
        	// 접근
        String accessToken = Jwts.builder()
        		.setSubject("access-token")
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims(데이터 단위) 설정
                .signWith(SignatureAlgorithm.HS256, SecretKey) // HS256과 비밀Key로 Sign(서명)
                .setExpiration(new Date(now + accessValidTime))
                .compact(); // 토큰 생성
        
        	// 재발급만
        String refreshToken = Jwts.builder()
        		.setSubject("refresh-token")
                .setHeader(headers)
                .setClaims(payloads) 
                .signWith(SignatureAlgorithm.HS256, SecretKey) 
                .setExpiration(new Date(now + refreshValidTime))
                .compact(); 
        
        System.out.println("accessToken : " + accessToken);
        System.out.println("refreshToken : " + refreshToken);
        
        String jwt = null;
		return jwt;
	}


}
