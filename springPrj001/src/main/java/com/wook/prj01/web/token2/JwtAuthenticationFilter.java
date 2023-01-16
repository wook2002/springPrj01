package com.wook.prj01.web.token2;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wook.prj01.web.token.JwtTokenProvider;

// extends Filter
// Spring의 설정 정보를 가져올 수 있게 확장이라함
// spring-session? SessionFilter(https://jerry92k.tistory.com/25)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider = null;
	
		// https://velog.io/@chullll/Spring-Security-JWT-%ED%95%84%ED%84%B0-%EC%A0%81%EC%9A%A9-%EA%B3%BC%EC%A0%95
		// https://imbf.github.io/spring/2020/06/29/Spring-Security-with-JWT.html
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException {
			System.out.println("----필터s----");
			
			String jwtToken = parseJwt(request);
			System.out.println("jwtToken : " + jwtToken);
			
			if (jwtToken != null && jwtTokenProvider.validateToken(jwtToken)) {
				System.out.println("true");
				
			}else {
				System.out.println(" false");
			}
			
			System.out.println("----필터e----");
			filterChain.doFilter(request, response);
		}
		
	
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		System.out.println("headerAuth : " + headerAuth);
		System.out.println("1 : " + StringUtils.hasText(headerAuth));
//		System.out.println("2 : " + headerAuth.startsWith("Bearer"));
//		System.out.println("3 : " + headerAuth.substring(7, headerAuth.length()));
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}

	

}
