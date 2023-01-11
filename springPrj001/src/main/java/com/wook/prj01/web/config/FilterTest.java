package com.wook.prj01.web.config;


import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.wook.prj01.web.member.dto.Member;

//https://jddng.tistory.com/269?category=1043529

@Component
public class FilterTest implements Filter{
	
//	private static final Logger logger = LoggerFactory.getLogger(MethodFilter.class);

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {System.out.println("FirstFilter가 생성 됩니다.");}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("---필터 시작 --");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String requestURI = httpRequest.getRequestURI();
	    String uuid = UUID.randomUUID().toString(); // HTTP 요청을 구분하기 위한 요청당 임의의 uuid 생성
	    System.out.println("requestURI : " + requestURI);
	    
	    HttpSession session = httpRequest.getSession(false);
	    System.out.println("session : " + session);
	 
	    boolean login = false;
	    
	    if(session != null) {
	    	System.out.println("test - 1");
	    	if(session.getAttribute("MEMBER") != null) {
	    		System.out.println("test - 2");
	    		login = true;
	    	}
	    }
	    
		
		chain.doFilter(request, response);
		System.out.println("---필터 종료 --");
	}
	
	@Override
    public void destroy() {System.out.println("FirstFilter가 사라집니다.");}

}
