package com.wook.prj01.web.token;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;


// @Slf4j : 로깅을 위한 라이브러리(log)
@Slf4j
@Component
public class JwtTokenProvider {
	
	 private final Key key;
	 
	 // Base64 : Binary Data를 Text로 바꾸는 Encoding
	 // HS256 (변조확인) -> 256bit 이상해야됨, (해시, 비밀키)
	 public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
		 System.out.println("prov-secretKey : " + secretKey);
	        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
	        this.key = Keys.hmacShaKeyFor(keyBytes);
	    }
	 
	 
	// 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
	    public TokenInfo generateToken(Authentication authentication) {
	        // 권한 가져오기
	        String authorities = authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.joining(","));
	 
	        long now = (new Date()).getTime();
	        System.out.println("prov-authorities : " + authorities);
	        
	        // Access Token 생성
	        // 1일: 24시 * 60분 * 60초 * 1000ms = 86400000s
	        Date accessTokenExpiresIn = new Date(now + 86400000);
	        String accessToken = Jwts.builder()
	                .setSubject(authentication.getName())
	                .claim("auth", authorities)
	                .setExpiration(accessTokenExpiresIn)
	                .signWith(key, SignatureAlgorithm.HS256)
	                .compact();
	        System.out.println("prov-accessToken : " + accessToken);
	 
	        // Refresh Token 생성
	        String refreshToken = Jwts.builder()
	                .setExpiration(new Date(now + 86400000))
	                .signWith(key, SignatureAlgorithm.HS256)
	                .compact();
	        System.out.println("prov-refreshToken : " + refreshToken);
	 
	        return TokenInfo.builder()
	                .grantType("Bearer")
	                .accessToken(accessToken)
	                .refreshToken(refreshToken)
	                .build();
	    }
	 
	    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
	    public Authentication getAuthentication(String accessToken) {
	        // 토큰 복호화
	        Claims claims = parseClaims(accessToken);
	        System.out.println("prov-accessToken : " + accessToken);
	 
	        if (claims.get("auth") == null) {
	            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
	        }
	 
	        // 클레임에서 권한 정보 가져오기
	        Collection<? extends GrantedAuthority> authorities =
	                Arrays.stream(claims.get("auth").toString().split(","))
	                        .map(SimpleGrantedAuthority::new)
	                        .collect(Collectors.toList());
	        System.out.println("prov-authorities : " + authorities);
	 
	        // UserDetails 객체를 만들어서 Authentication 리턴
	        UserDetails principal = new User(claims.getSubject(), "", authorities);
	        System.out.println("prov-principal ; " + principal);
	        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
	    }
	 
	    // 토큰 정보를 검증하는 메서드
	    public boolean validateToken(String token) {
	    	System.out.println("prov-token검증 : " + token);
	        try {
	            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
	            return true;
	        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
	            log.info("Invalid JWT Token", e);
	        } catch (ExpiredJwtException e) {
	            log.info("Expired JWT Token", e);
	        } catch (UnsupportedJwtException e) {
	            log.info("Unsupported JWT Token", e);
	        } catch (IllegalArgumentException e) {
	            log.info("JWT claims string is empty.", e);
	        }
	        return false;
	    }
	 
	    private Claims parseClaims(String accessToken) {
	    	System.out.println("prov-parseClaims-accessToken : " + accessToken);
	        try {
	            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
	        } catch (ExpiredJwtException e) {
	            return e.getClaims();
	        }
	    }

}
